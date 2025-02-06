package com.global.order.api.master.service;

import com.global.order.core.domain.SalesOrderKREntity;

import java.util.Optional;

public interface SalesOrderLocalService {
    Optional<SalesOrderKREntity> findById(Long id);
}
