package com.global.order.worker.facade;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

public interface SalesOrderCrudMessageFacade {
    void executeSalesOrderMaster(List<ConsumerRecord<String, Object>> records);
}
