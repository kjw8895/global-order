package com.global.order.api.master.facade;

import com.global.order.common.code.RegionCode;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.application.dto.request.SalesOrderMasterRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesOrderMasterFacade {
    SalesOrderMasterEntityDto findOne(Long salesOrderId, RegionCode regionCode);
    Page<SalesOrderMasterEntityDto> page(Pageable pageable, SalesOrderMasterRequestDto request);
}
