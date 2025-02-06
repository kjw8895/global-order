package com.global.order.core.domain;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Deprecated
@Getter
@Entity
@Table(name = "sales_order_kr")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesOrderKREntity extends BaseEntity {
    @Id
    @Tsid
    @Column(name = "id")
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "payment_datetime")
    private LocalDateTime paymentDatetime;

    public void updateTest() {
        this.paymentDatetime = LocalDateTime.now();
    }

    public static SalesOrderKREntity toEntity(Long id) {
        SalesOrderKREntity entity = new SalesOrderKREntity();
        entity.userId = id;
        entity.orderNumber = UUID.randomUUID().toString();
        entity.paymentDatetime = LocalDateTime.now();

        return entity;
    }
}