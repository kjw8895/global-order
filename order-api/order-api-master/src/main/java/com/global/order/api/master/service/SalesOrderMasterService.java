package com.global.order.api.master.service;

import com.global.order.common.code.RegionCode;
import com.global.order.core.application.dto.request.SalesOrderMasterRequestDto;
import com.global.order.core.domain.SalesOrderMasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SalesOrderMasterService {
    Optional<SalesOrderMasterEntity> findOne(Long salesOrderId, RegionCode regionCode);
    Page<SalesOrderMasterEntity> page(Pageable pageable, SalesOrderMasterRequestDto request);
}
