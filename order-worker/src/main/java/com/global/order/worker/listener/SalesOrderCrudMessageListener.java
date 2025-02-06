package com.global.order.worker.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

public interface SalesOrderCrudMessageListener {
    void executeSalesOrderMaster(List<ConsumerRecord<String, Object>> records, Acknowledgment acknowledgment);
}
