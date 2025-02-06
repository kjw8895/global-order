package com.global.order.worker.facade.impl;

import com.global.order.common.application.message.SalesOrderApiMessage;
import com.global.order.common.application.message.SalesOrderLocalMessage;
import com.global.order.common.code.RegionCode;
import com.global.order.common.utils.ObjectMapperUtils;
import com.global.order.worker.facade.SalesOrderLocalMessageFacade;
import com.global.order.worker.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderLocalMessageFacadeImpl implements SalesOrderLocalMessageFacade {
    private final KafkaProducerService kafkaProducerService;

    @Override
    public void sendToApiTopic(ConsumerRecord<String, Object> record, RegionCode regionCode) {
        SalesOrderLocalMessage message = null;

        try {
            message = ObjectMapperUtils.valueToObject(record.value(), SalesOrderLocalMessage.class);

            log.debug("sales-order-local record : {}", message);

            message.validation(regionCode);

            kafkaProducerService.sendToOrderApi(SalesOrderApiMessage.toMessage(message));
        } catch (Exception e) {
            // 비정상 메시지 DLQ 처리
            log.error("error : sales-order-local record : {}", record);
            log.error(e.getMessage(), e);
            kafkaProducerService.sendToDlq(message, e);

            throw e;
        }
    }
}
