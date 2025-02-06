package com.global.order.worker.facade.impl;

import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.application.message.SalesOrderMasterMessage;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import com.global.order.common.exception.CommonException;
import com.global.order.common.utils.ObjectMapperUtils;
import com.global.order.worker.exception.WorkerExceptionCode;
import com.global.order.worker.facade.SalesOrderCrudMessageFacade;
import com.global.order.worker.service.KafkaProducerService;
import com.global.order.worker.service.SalesOrderMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderCrudMessageFacadeImpl implements SalesOrderCrudMessageFacade {
    private final KafkaProducerService kafkaProducerService;
    private final SalesOrderMasterService salesOrderMasterService;

    @Transactional
    @Override
    public void executeSalesOrderMaster(List<ConsumerRecord<String, Object>> records) {
        if (ObjectUtils.isEmpty(records)) {
            return;
        }

        log.debug("sales order master crud records : {}", records);

        List<SalesOrderCrudMessage> messages = null;

        try {
            messages = records.stream()
                    .map(ConsumerRecord::value)
                    .map(value -> ObjectMapperUtils.valueToObject(value, SalesOrderCrudMessage.class))
                    .toList();

            Map<RegionCode, Map<MessageMethodType, List<SalesOrderCrudMessage>>> map = groupingMessages(messages);
            map.forEach((regionCode, value) -> {
                value.forEach((methodType, messageList) -> {
                    try {
                        // Database execute
                        salesOrderMasterService.execute(regionCode, methodType, messageList);
                        List<SalesOrderMasterMessage> salesOrderMasterMessageList = messageList.stream().map(SalesOrderCrudMessage::getDto).map(dto -> SalesOrderMasterMessage.toMessage(dto.getId(), regionCode, methodType)).toList();
                        // 다른 MSA 서버에서 사용 할 데이터
                        for (SalesOrderMasterMessage salesOrderMasterMessage : salesOrderMasterMessageList) {
                            kafkaProducerService.sendToOrderMaster(salesOrderMasterMessage);
                        }
                    } catch (Exception e) {
                        log.error("error : sales order master crud message : {}", messageList, e);
                        kafkaProducerService.sendToDlq(messageList, e);
                    }
                });
            });
        } catch (Exception e) {
            log.error("error : sales order master crud messages", e);
            kafkaProducerService.sendToDlq(messages, e);
            throw e;
        }
    }

    private Map<RegionCode, Map<MessageMethodType, List<SalesOrderCrudMessage>>> groupingMessages(List<SalesOrderCrudMessage> messages) {
        try {
            return messages.stream()
                    .collect(Collectors.groupingBy(
                            SalesOrderCrudMessage::getRegionCode,
                            Collectors.groupingBy(SalesOrderCrudMessage::getMethodType)
                    ));
        } catch (Exception e) {
            log.error("error : sales order master crud messages grouping failed : {}", messages);
            log.error(e.getMessage(), e);

            throw new CommonException(WorkerExceptionCode.MESSAGE_GROUPING_FAILED);
        }
    }
}
