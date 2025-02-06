package com.global.order.core.repository.custom;

import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.domain.SalesOrderMasterEntity;

import java.util.List;

public interface CustomSalesOrderMasterEntityRepository {
    void bulkInsert(List<SalesOrderMasterEntity> entities);
    void bulkUpdate(List<SalesOrderMasterEntityDto> dtoList);
}
