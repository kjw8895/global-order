package com.global.order.worker.service;

import com.global.order.common.application.dto.SalesOrderDto;
import com.global.order.common.code.RegionCode;

public interface OrderWebClientService {
    SalesOrderDto findOrderByOrderId(Long id, RegionCode regionCode);
}
