package com.global.order.worker.listener.impl;

import com.global.order.client.kafka.config.property.KafkaTopicProperties;
import com.global.order.worker.facade.SalesOrderApiMessageFacade;
import com.global.order.worker.listener.SalesOrderApiMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaTopicProperties.class})
public class SalesOrderApiMessageListenerImpl implements SalesOrderApiMessageListener {
    private final SalesOrderApiMessageFacade facade;

    @Override
    @KafkaListener(topics = "#{@salesOrderApiKrTopic}", groupId = "order-sales-order-api-kr", concurrency = "2")
    public void consume(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment) {
        log.debug("KR - sales-order-api-kr record received: {}", record);

        try {
            facade.requestApi(record.value());
        } catch (Exception e) {
            log.error("error : sales-order-api", e);
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
