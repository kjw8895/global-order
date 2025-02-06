package com.global.order.core.repository;

import com.global.order.common.code.RegionCode;
import com.global.order.core.domain.SalesOrderMasterEntity;
import com.global.order.core.repository.custom.CustomSalesOrderMasterEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesOrderMasterEntityRepository extends JpaRepository<SalesOrderMasterEntity, Long>, CustomSalesOrderMasterEntityRepository {
    Optional<SalesOrderMasterEntity> findBySalesOrderIdAndRegionCode(Long salesOrderId, RegionCode regionCode);
}
