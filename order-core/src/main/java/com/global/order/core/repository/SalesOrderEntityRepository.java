package com.global.order.core.repository;

import com.global.order.core.domain.SalesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderEntityRepository extends JpaRepository<SalesOrderEntity, Long> {
}
