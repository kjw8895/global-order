package com.global.order.worker.listener.impl;

import com.global.order.common.code.RegionCode;
import com.global.order.worker.facade.SalesOrderLocalMessageFacade;
import com.global.order.worker.listener.SalesOrderLocalMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderLocalMessageListenerKR implements SalesOrderLocalMessageListener {
    private final SalesOrderLocalMessageFacade facade;

    @Override
    @KafkaListener(topics = "#{@salesOrderLocalKrTopic}", groupId = "order-sales-order-local-kr", concurrency = "2")
    public void consume(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment) {
        log.debug("KR - sales-order-local-kr record received: {}", record);

        try {
            facade.sendToApiTopic(record, RegionCode.KR);
        } catch (Exception e) {
            log.error("error : sales-order-local-kr", e);
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
