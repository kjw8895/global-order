package com.global.order.worker.service;

import com.global.order.common.application.message.SalesOrderCrudMessage;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;

import java.util.List;

public interface SalesOrderMasterService {
    void execute(RegionCode regionCode, MessageMethodType methodType, List<SalesOrderCrudMessage> messages);
    void bulkInsert(List<SalesOrderMasterEntityDto> dtoList);
    void bulkUpdate(List<SalesOrderMasterEntityDto> dtoList);
}
