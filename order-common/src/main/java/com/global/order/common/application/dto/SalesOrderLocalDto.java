package com.global.order.common.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesOrderLocalDto {
    private Long id;
    private Long userId;
    private String orderNumber;
    private LocalDateTime paymentDatetime;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;

    public static SalesOrderLocalDto toDto(Long id, Long userId, String orderNumber, LocalDateTime paymentDatetime, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
        SalesOrderLocalDto dto = new SalesOrderLocalDto();
        dto.id = id;
        dto.userId = userId;
        dto.orderNumber = orderNumber;
        dto.paymentDatetime = paymentDatetime;
        dto.createdDatetime = createdDatetime;
        dto.modifiedDatetime = modifiedDatetime;

        return dto;
    }
}
