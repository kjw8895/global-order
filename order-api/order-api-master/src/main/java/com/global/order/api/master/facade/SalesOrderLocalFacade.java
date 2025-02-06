package com.global.order.api.master.facade;

import com.global.order.common.application.dto.SalesOrderLocalDto;

public interface SalesOrderLocalFacade {
    SalesOrderLocalDto findById(Long id);
}
