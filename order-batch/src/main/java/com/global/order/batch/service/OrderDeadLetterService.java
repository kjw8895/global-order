package com.global.order.batch.service;

public interface OrderDeadLetterService {
    void retry(Object message);
}
