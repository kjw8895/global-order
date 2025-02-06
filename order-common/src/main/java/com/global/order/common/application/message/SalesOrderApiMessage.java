package com.global.order.common.application.message;

import com.global.order.common.code.DlqType;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class SalesOrderApiMessage extends DlqMessage {
    // 이벤트 수신 대상 private key
    private Long id;
    // 이벤트 행위
    private MessageMethodType methodType;
    // 국가 코드
    private RegionCode regionCode;
    // 메시지 최초 생성 시간
    private Long publishedTimestamp;

    public SalesOrderApiMessage(SalesOrderLocalMessage salesOrderLocalMessage) {
        super(DlqType.SALES_ORDER_API);
        this.id = salesOrderLocalMessage.getId();
        this.methodType = salesOrderLocalMessage.getMethodType();
        this.regionCode = salesOrderLocalMessage.getRegionCode();
        this.publishedTimestamp = salesOrderLocalMessage.getPublishedTimestamp();
    }

    public static SalesOrderApiMessage toMessage(SalesOrderLocalMessage salesOrderLocalMessage) {
        return new SalesOrderApiMessage(salesOrderLocalMessage);
    }

}
