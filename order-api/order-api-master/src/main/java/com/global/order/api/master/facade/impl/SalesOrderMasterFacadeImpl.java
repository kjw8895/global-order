package com.global.order.api.master.facade.impl;

import com.global.order.api.master.facade.SalesOrderMasterFacade;
import com.global.order.api.master.service.SalesOrderMasterService;
import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.code.RegionCode;
import com.global.order.common.exception.CommonException;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.application.dto.request.SalesOrderMasterRequestDto;
import com.global.order.core.domain.SalesOrderMasterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalesOrderMasterFacadeImpl implements SalesOrderMasterFacade {
    private final SalesOrderMasterService salesOrderMasterService;

    @Override
    public SalesOrderMasterEntityDto findOne(Long salesOrderId, RegionCode regionCode) {
        SalesOrderMasterEntity entity = salesOrderMasterService.findOne(salesOrderId, regionCode).orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));
        return SalesOrderMasterEntityDto.toDto(entity);
    }

    @Override
    public Page<SalesOrderMasterEntityDto> page(Pageable pageable, SalesOrderMasterRequestDto request) {
        Page<SalesOrderMasterEntity> page = salesOrderMasterService.page(pageable, request);
        return page.map(SalesOrderMasterEntityDto::toDto);
    }
}
