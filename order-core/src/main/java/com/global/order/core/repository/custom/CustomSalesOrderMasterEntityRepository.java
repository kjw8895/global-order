package com.global.order.core.repository.custom;

import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.application.dto.request.SalesOrderMasterRequestDto;
import com.global.order.core.domain.SalesOrderMasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomSalesOrderMasterEntityRepository {
    Page<SalesOrderMasterEntity> page(Pageable pageable, SalesOrderMasterRequestDto request);
    void bulkInsert(List<SalesOrderMasterEntity> entities);
    void bulkUpdate(List<SalesOrderMasterEntityDto> dtoList);
}
