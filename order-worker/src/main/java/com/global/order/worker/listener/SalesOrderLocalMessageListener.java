package com.global.order.worker.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface SalesOrderLocalMessageListener {
    void consume(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment);
}
