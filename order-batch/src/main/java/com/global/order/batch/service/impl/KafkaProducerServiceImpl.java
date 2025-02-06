package com.global.order.batch.service.impl;

import com.global.order.batch.service.KafkaProducerService;
import com.global.order.client.kafka.config.property.KafkaTopicProperties;
import com.global.order.client.kafka.service.KafkaProducerCluster;
import com.global.order.common.application.message.DlqMessage;
import com.global.order.common.application.message.SalesOrderApiMessage;
import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.application.message.SalesOrderLocalMessage;
import com.global.order.common.code.MessageCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaTopicProperties.class})
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaProducerCluster cluster;
    private final KafkaTopicProperties kafkaTopicProperties;

    @Override
    public void sendToOrderLocal(SalesOrderLocalMessage message) {
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_LOCAL, message.getRegionCode()));
    }

    @Override
    public void sendToOrderApi(SalesOrderApiMessage message) {
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_API, message.getRegionCode()));
    }

    @Override
    public void sendToOrderCrud(SalesOrderCrudMessage message) {
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_CRUD));
    }

    @Override
    public <T extends DlqMessage> void sendToDiscard(T message) {
        log.info("Sending message to discard topic");
        send(message, kafkaTopicProperties.getName(MessageCategory.ORDER_DISCARD));
    }

    private void send(Object message, String topic) {
        cluster.sendMessage(message, topic);
    }
}
