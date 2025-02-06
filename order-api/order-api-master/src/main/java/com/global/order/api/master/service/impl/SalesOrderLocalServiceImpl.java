package com.global.order.api.master.service.impl;

import com.global.order.api.master.service.SalesOrderLocalService;
import com.global.order.core.domain.SalesOrderKREntity;
import com.global.order.core.repository.SalesOrderKREntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesOrderLocalServiceImpl implements SalesOrderLocalService {
    private final SalesOrderKREntityRepository salesOrderKREntityRepository;

    @Override
    public Optional<SalesOrderKREntity> findById(Long id) {
        return salesOrderKREntityRepository.findById(id);
    }
}
