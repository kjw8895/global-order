package com.global.order.core.repository;

import com.global.order.core.domain.SalesOrderMasterEntity;
import com.global.order.core.repository.custom.CustomSalesOrderMasterEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderMasterEntityRepository extends JpaRepository<SalesOrderMasterEntity, Long>, CustomSalesOrderMasterEntityRepository {
}
