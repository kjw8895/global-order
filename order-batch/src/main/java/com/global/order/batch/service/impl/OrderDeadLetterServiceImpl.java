package com.global.order.batch.service.impl;

import com.global.order.batch.code.BatchExceptionCode;
import com.global.order.batch.service.KafkaProducerService;
import com.global.order.batch.service.OrderDeadLetterService;
import com.global.order.client.kafka.config.property.KafkaConsumerProperties;
import com.global.order.common.application.message.DlqMessage;
import com.global.order.common.application.message.SalesOrderApiMessage;
import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.application.message.SalesOrderLocalMessage;
import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.code.DlqType;
import com.global.order.common.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaConsumerProperties.class})
public class OrderDeadLetterServiceImpl implements OrderDeadLetterService {
    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerProperties kafkaConsumerProperties;

    @Override
    public void retry(Object message) {
        DlqType type = ObjectMapperUtils.getFieldValueFromString(message.toString(), "type", DlqType.class);
        log.info("dlq type : {}", type);

        switch (type) {
            case SALES_ORDER_LOCAL -> salesOrderLocal(message);
            case SALES_ORDER_API -> salesOrderApi(message);
            case SALES_ORDER_MASTER_SAVE -> salesOrderDatabaseDriver(message);
        }
    }

    private void salesOrderLocal(Object message) {
        List<Integer> discardCode = Arrays.asList(CommonExceptionCode.INVALID_REQUEST.getCode(), BatchExceptionCode.EMPTY_MESSAGE.getCode());
        SalesOrderLocalMessage salesOrderLocalMessage = convertMessage(message, SalesOrderLocalMessage.class);
        salesOrderLocalMessage.increaseFailedCount();

        if (invalid(salesOrderLocalMessage) || discardCode.contains(salesOrderLocalMessage.getError().getCode())) {
            kafkaProducerService.sendToDiscard(salesOrderLocalMessage);
        } else {
            kafkaProducerService.sendToOrderLocal(salesOrderLocalMessage);
        }
    }

    private void salesOrderApi(Object message) {
        SalesOrderApiMessage salesOrderApiMessage = convertMessage(message, SalesOrderApiMessage.class);
        salesOrderApiMessage.increaseFailedCount();

        if (invalid(salesOrderApiMessage)) {
            kafkaProducerService.sendToDiscard(salesOrderApiMessage);
        } else {
            kafkaProducerService.sendToOrderApi(salesOrderApiMessage);
        }
    }

    private void salesOrderDatabaseDriver(Object message) {
        SalesOrderCrudMessage salesOrderCrudMessage = convertMessage(message, SalesOrderCrudMessage.class);
        salesOrderCrudMessage.increaseFailedCount();

        if (invalid(salesOrderCrudMessage)) {
            kafkaProducerService.sendToDiscard(salesOrderCrudMessage);
        } else {
            kafkaProducerService.sendToOrderCrud(salesOrderCrudMessage);
        }
    }

    private <T> T convertMessage(Object message, Class<T> clz) {
        return ObjectMapperUtils.valueToObject(message, clz);
    }

    private <T extends DlqMessage> boolean invalid(T message) {
        return message.discard(kafkaConsumerProperties.getOption().getMaxFailCount());
    }
}
