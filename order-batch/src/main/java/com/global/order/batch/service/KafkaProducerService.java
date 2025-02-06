package com.global.order.batch.service;

import com.global.order.common.application.message.DlqMessage;
import com.global.order.common.application.message.SalesOrderApiMessage;
import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.application.message.SalesOrderLocalMessage;

public interface KafkaProducerService {
    void sendToOrderLocal(SalesOrderLocalMessage message);
    void sendToOrderApi(SalesOrderApiMessage message);
    void sendToOrderCrud(SalesOrderCrudMessage message);
    <T extends DlqMessage> void sendToDiscard(T message);
}
