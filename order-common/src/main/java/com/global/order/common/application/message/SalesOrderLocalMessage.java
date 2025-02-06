package com.global.order.common.application.message;

import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.code.DlqType;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import com.global.order.common.exception.CommonException;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@ToString
public class SalesOrderLocalMessage extends DlqMessage {
    // 이벤트 수신 대상 private key
    private Long id;
    // 이벤트 행위
    private MessageMethodType methodType;
    // 국가 코드
    private RegionCode regionCode;
    // 메시지 최초 생성 시간
    private Long publishedTimestamp;

    public SalesOrderLocalMessage() {
        super(DlqType.SALES_ORDER_LOCAL);
    }

    public void validation(RegionCode correctRegionCode) {
        if (Stream.of(id, methodType, regionCode, publishedTimestamp).anyMatch(Objects::isNull)) {
            throw new CommonException(CommonExceptionCode.INVALID_REQUEST);
        }

        if (!correctRegionCode.equals(regionCode)) {
            throw new CommonException(CommonExceptionCode.INVALID_REQUEST);
        }
    }
}
