package com.global.order.worker.service.impl;

import com.global.order.client.kafka.config.property.KafkaTopicProperties;
import com.global.order.client.kafka.service.KafkaProducerCluster;
import com.global.order.common.application.message.*;
import com.global.order.common.code.MessageCategory;
import com.global.order.common.exception.CommonException;
import com.global.order.worker.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaTopicProperties.class})
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaProducerCluster cluster;
    private final KafkaTopicProperties kafkaTopicProperties;

    @Override
    public void sendToOrderApi(SalesOrderApiMessage message) {
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_API, message.getRegionCode()));
    }

    @Override
    public void sendToOrderCrud(SalesOrderCrudMessage message) {
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_CRUD));
    }

    @Override
    public void sendToOrderMaster(SalesOrderMasterMessage message) {
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_MASTER));
    }

    @Override
    public <T extends DlqMessage> void sendToDlq(List<T> messages, Exception currentException) {
        if (ObjectUtils.isEmpty(messages)) {
            return;
        }

        for (T message : messages) {
            sendToDlq(message, currentException);
        }
    }

    @Override
    public <T extends DlqMessage> void sendToDlq(T message, Exception currentException) {
        if (ObjectUtils.isEmpty(message)) {
            return;
        }

        try {
            if (currentException instanceof CommonException commonException) {
                message.fail(CustomErrorMessage.toMessage(commonException.getCode(), commonException));
            } else {
                message.fail(CustomErrorMessage.toMessage(currentException));
            }
            log.info("Sending message to dead letter topic");

            send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_DLQ));
        } catch (Exception e) {
            log.error("error : send message to sales-order-dead-letter failed : {}", message);
            log.error(e.getMessage(), e);
        }
    }

    private void send(Object message, String topic) {
        cluster.sendMessage(message, topic);
    }
}
