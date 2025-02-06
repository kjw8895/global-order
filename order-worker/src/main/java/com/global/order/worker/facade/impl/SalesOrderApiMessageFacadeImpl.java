package com.global.order.worker.facade.impl;

import com.global.order.common.application.dto.SalesOrderDto;
import com.global.order.common.application.message.SalesOrderApiMessage;
import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.utils.ObjectMapperUtils;
import com.global.order.worker.facade.SalesOrderApiMessageFacade;
import com.global.order.worker.service.KafkaProducerService;
import com.global.order.worker.service.OrderWebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderApiMessageFacadeImpl implements SalesOrderApiMessageFacade {
    private final KafkaProducerService kafkaProducerService;
    private final OrderWebClientService webClientService;

    @Transactional
    @Override
    public void requestApi(Object record) {
        SalesOrderApiMessage message = null;

        try {
            message = ObjectMapperUtils.valueToObject(record, SalesOrderApiMessage.class);

            // api 호출
            SalesOrderDto dto = webClientService.findOrderByOrderId(message.getId(), message.getRegionCode());

            // 메세지 발행
            kafkaProducerService.sendToOrderCrud(SalesOrderCrudMessage.toMessage(message, dto));
        } catch (Exception e) {
            log.error("error : sales order api record : {}", record);
            log.error(e.getMessage(), e);
            kafkaProducerService.sendToDlq(message, e);

            throw e;
        }
    }
}
