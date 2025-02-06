package com.global.order.core.application.dto.request;

import com.global.order.common.code.RegionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesOrderMasterRequestDto {
    private RegionCode regionCode;
    private Long userId;
}
