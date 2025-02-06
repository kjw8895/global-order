package com.global.order.api.master.facade.impl;

import com.global.order.api.master.facade.SalesOrderLocalFacade;
import com.global.order.api.master.service.SalesOrderLocalService;
import com.global.order.common.application.dto.SalesOrderLocalDto;
import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.exception.CommonException;
import com.global.order.core.domain.SalesOrderKREntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderLocalFacadeImpl implements SalesOrderLocalFacade {
    private final SalesOrderLocalService salesOrderLocalService;

    @Override
    public SalesOrderLocalDto findById(Long id) {
        SalesOrderKREntity entity = salesOrderLocalService.findById(id).orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));
        return SalesOrderLocalDto.toDto(entity.getId(), entity.getUserId(), entity.getOrderNumber(), entity.getPaymentDatetime(), entity.getCreatedDatetime(), entity.getModifiedDatetime());
    }
}