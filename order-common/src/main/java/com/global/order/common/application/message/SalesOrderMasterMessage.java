package com.global.order.common.application.message;

import com.global.order.common.code.DlqType;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class SalesOrderMasterMessage extends DlqMessage {
    private Long salesOrderId;
    private RegionCode regionCode;
    private MessageMethodType methodType;

    public SalesOrderMasterMessage(Long salesOrderId, RegionCode regionCode, MessageMethodType methodType) {
        super(DlqType.SALES_ORDER_MASTER);
        this.salesOrderId = salesOrderId;
        this.regionCode = regionCode;
        this.methodType = methodType;
    }

    public static SalesOrderMasterMessage toMessage(Long salesOrderId, RegionCode regionCode, MessageMethodType methodType) {
        return new SalesOrderMasterMessage(salesOrderId, regionCode, methodType);
    }
}
