package com.global.order.common.application.message;

import com.global.order.common.application.dto.SalesOrderDto;
import com.global.order.common.code.DlqType;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class SalesOrderCrudMessage extends DlqMessage {
    private MessageMethodType methodType;
    private RegionCode regionCode;
    private SalesOrderDto dto;

    public SalesOrderCrudMessage(SalesOrderApiMessage salesOrderApiMessage, SalesOrderDto dto) {
        super(DlqType.SALES_ORDER_MASTER_SAVE);
        this.methodType = salesOrderApiMessage.getMethodType();
        this.regionCode = salesOrderApiMessage.getRegionCode();
        this.dto = dto;
    }

    @Deprecated
    public SalesOrderCrudMessage(MessageMethodType methodType, RegionCode regionCode, Long publishedTimestamp, SalesOrderDto dto) {
        super(DlqType.SALES_ORDER_MASTER_SAVE);
        this.methodType = methodType;
        this.regionCode = regionCode;
        this.dto = dto;
    }

    public static SalesOrderCrudMessage toMessage(SalesOrderApiMessage salesOrderApiMessage, SalesOrderDto dto) {
        return new SalesOrderCrudMessage(salesOrderApiMessage, dto);
    }
}
