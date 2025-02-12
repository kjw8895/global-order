package com.global.order.core.application.dto;

import com.global.order.common.application.dto.SalesOrderLocalDto;
import com.global.order.common.code.RegionCode;
import com.global.order.core.domain.SalesOrderMasterEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderMasterEntityDto {
    private Long id;
    private Long userId;
    private Long salesOrderId;
    private String orderNumber;
    private RegionCode regionCode;
    private LocalDateTime paymentDatetime;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;

    public static SalesOrderMasterEntityDto toDto(SalesOrderLocalDto salesOrderLocalDto, RegionCode regionCode) {
        SalesOrderMasterEntityDto dto = new SalesOrderMasterEntityDto();
        dto.salesOrderId = salesOrderLocalDto.getId();
        dto.userId = salesOrderLocalDto.getUserId();
        dto.orderNumber = salesOrderLocalDto.getOrderNumber();
        dto.regionCode = regionCode;
        dto.paymentDatetime = salesOrderLocalDto.getPaymentDatetime();
        dto.createdDatetime = salesOrderLocalDto.getCreatedDatetime();
        dto.modifiedDatetime = salesOrderLocalDto.getModifiedDatetime();

        return dto;
    }

    public static SalesOrderMasterEntityDto toDto(SalesOrderMasterEntity entity) {
        SalesOrderMasterEntityDto dto = new SalesOrderMasterEntityDto();
        dto.id = entity.getId();
        dto.salesOrderId = entity.getSalesOrderId();
        dto.userId = entity.getUserId();
        dto.orderNumber = entity.getOrderNumber();
        dto.regionCode = entity.getRegionCode();
        dto.paymentDatetime = entity.getPaymentDatetime();
        dto.createdDatetime = entity.getCreatedDatetime();
        dto.modifiedDatetime = entity.getModifiedDatetime();

        return dto;
    }
}
