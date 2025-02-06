package com.global.order.worker.service;

import com.global.order.common.application.message.DlqMessage;
import com.global.order.common.application.message.SalesOrderApiMessage;
import com.global.order.common.application.message.SalesOrderCrudMessage;

import java.util.List;

public interface KafkaProducerService {
    void sendToOrderApi(SalesOrderApiMessage message);
    void sendToOrderCrud(SalesOrderCrudMessage message);
    <T extends DlqMessage> void sendToDlq(List<T> messages, Exception currentException);
    <T extends DlqMessage> void sendToDlq(T message, Exception currentException);
}
