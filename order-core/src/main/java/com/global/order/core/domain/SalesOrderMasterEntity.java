package com.global.order.core.domain;

import com.global.order.common.code.RegionCode;
import com.global.order.core.annotation.CustomTsid;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "sales_order_master")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesOrderMasterEntity {
    @Id
    @CustomTsid
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "region_code")
    private RegionCode regionCode;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "sales_order_id")
    private Long salesOrderId;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "payment_datetime")
    private LocalDateTime paymentDatetime;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @Column(name = "modified_datetime")
    private LocalDateTime modifiedDatetime;

    @Version
    private Long version;

    public void updateTsid(Long tsid) {
        this.id = tsid;
    }

    public static SalesOrderMasterEntity toEntity(SalesOrderMasterEntityDto dto) {
        SalesOrderMasterEntity entity = new SalesOrderMasterEntity();
        entity.id = dto.getId();
        entity.regionCode = dto.getRegionCode();
        entity.userId = dto.getUserId();
        entity.salesOrderId = dto.getSalesOrderId();
        entity.orderNumber = dto.getOrderNumber();
        entity.paymentDatetime = dto.getPaymentDatetime();
        entity.createdDatetime = dto.getCreatedDatetime();
        entity.modifiedDatetime = dto.getModifiedDatetime();

        return entity;
    }
}
