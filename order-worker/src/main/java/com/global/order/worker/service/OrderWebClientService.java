package com.global.order.worker.service;

import com.global.order.common.application.dto.SalesOrderLocalDto;
import com.global.order.common.code.RegionCode;

public interface OrderWebClientService {
    SalesOrderLocalDto findOrderByOrderId(Long id, RegionCode regionCode);
}
