package com.global.order.worker.service.impl;

import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.domain.SalesOrderMasterEntity;
import com.global.order.core.repository.SalesOrderMasterEntityRepository;
import com.global.order.worker.service.SalesOrderMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesOrderMasterServiceImpl implements SalesOrderMasterService {
    private final SalesOrderMasterEntityRepository repository;

    @Override
    public void execute(RegionCode regionCode, MessageMethodType methodType, List<SalesOrderCrudMessage> messages) {
        List<SalesOrderMasterEntityDto> dtoList = messages.stream().map(SalesOrderCrudMessage::getDto).map(dto -> SalesOrderMasterEntityDto.toDto(dto, regionCode)).toList();
        switch (methodType) {
            case POST -> bulkInsert(dtoList);
            case PUT -> bulkUpdate(dtoList);
        }
    }

    @Override
    public void bulkInsert(List<SalesOrderMasterEntityDto> dtoList) {
        try {
            List<SalesOrderMasterEntity> entities = dtoList.stream().map(SalesOrderMasterEntity::toEntity).toList();
            repository.bulkInsert(entities);
        } catch (DataAccessException e) {
            log.error("error : SalesOrderMasterEntity bulkInsert failed - msg : {}, cause : {}", e.getMessage(), e.getCause(), e);
            throw e;
        } catch (Exception e) {
            log.error("error : SalesOrderMasterEntity bulkInsert failed", e);
            throw e;
        }
    }

    @Override
    public void bulkUpdate(List<SalesOrderMasterEntityDto> dtoList) {
        try {
            repository.bulkUpdate(dtoList);
        } catch (DataAccessException e) {
            log.error("error : SalesOrderMasterEntity bulkInsert failed - msg : {}, cause : {}", e.getMessage(), e.getCause(), e);
            throw e;
        } catch (Exception e) {
            log.error("error : SalesOrderMasterEntity bulkInsert failed", e);
            throw e;
        }
    }
}
