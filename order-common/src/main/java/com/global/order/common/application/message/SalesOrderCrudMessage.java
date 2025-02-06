package com.global.order.common.application.message;

import com.global.order.common.application.dto.SalesOrderLocalDto;
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
    private SalesOrderLocalDto dto;

    public SalesOrderCrudMessage(SalesOrderApiMessage salesOrderApiMessage, SalesOrderLocalDto dto) {
        super(DlqType.SALES_ORDER_MASTER_SAVE);
        this.methodType = salesOrderApiMessage.getMethodType();
        this.regionCode = salesOrderApiMessage.getRegionCode();
        this.dto = dto;
    }

    @Deprecated
    public SalesOrderCrudMessage(MessageMethodType methodType, RegionCode regionCode, Long publishedTimestamp, SalesOrderLocalDto dto) {
        super(DlqType.SALES_ORDER_MASTER_SAVE);
        this.methodType = methodType;
        this.regionCode = regionCode;
        this.dto = dto;
    }

    public static SalesOrderCrudMessage toMessage(SalesOrderApiMessage salesOrderApiMessage, SalesOrderLocalDto dto) {
        return new SalesOrderCrudMessage(salesOrderApiMessage, dto);
    }
}
