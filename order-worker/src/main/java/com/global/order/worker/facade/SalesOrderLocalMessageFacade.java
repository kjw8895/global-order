package com.global.order.worker.facade;

import com.global.order.common.code.RegionCode;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface SalesOrderLocalMessageFacade {
    void sendToApiTopic(ConsumerRecord<String, Object> record, RegionCode regionCode);
}
