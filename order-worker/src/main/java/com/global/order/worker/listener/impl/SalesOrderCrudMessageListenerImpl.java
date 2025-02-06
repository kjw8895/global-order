package com.global.order.worker.listener.impl;

import com.global.order.worker.facade.SalesOrderCrudMessageFacade;
import com.global.order.worker.listener.SalesOrderCrudMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderCrudMessageListenerImpl implements SalesOrderCrudMessageListener {
    private final SalesOrderCrudMessageFacade facade;

    @Override
    @KafkaListener(topics = "#{@salesOrderCrudTopic}", groupId = "order-sales-order-crud", containerFactory = "kafkaBatchListenerContainerFactory", concurrency = "10")
    public void executeSalesOrderMaster(List<ConsumerRecord<String, Object>> records, Acknowledgment acknowledgment) {
        log.debug("sales-order-crud records size : {}", records.size());

        try {
            records.stream().map(ConsumerRecord::value).forEach(value -> log.info("{}", value));
            facade.executeSalesOrderMaster(records);
        } catch (Exception e) {
            log.error("error : sales-order-crud", e);
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
