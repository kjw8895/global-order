package com.global.order.api.master.service.impl;

import com.global.order.api.master.service.SalesOrderMasterService;
import com.global.order.common.code.RegionCode;
import com.global.order.core.application.dto.request.SalesOrderMasterRequestDto;
import com.global.order.core.domain.SalesOrderMasterEntity;
import com.global.order.core.repository.SalesOrderMasterEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesOrderMasterServiceImpl implements SalesOrderMasterService {
    private final SalesOrderMasterEntityRepository repository;

    @Override
    public Optional<SalesOrderMasterEntity> findOne(Long salesOrderId, RegionCode regionCode) {
        return repository.findBySalesOrderIdAndRegionCode(salesOrderId, regionCode);
    }

    @Override
    public Page<SalesOrderMasterEntity> page(Pageable pageable, SalesOrderMasterRequestDto request) {
        return repository.page(pageable, request);
    }
}
