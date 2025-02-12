package com.global.order.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DlqType implements CodeEnum {
    SALES_ORDER_LOCAL("SALES_ORDER_LOCAL"),
    SALES_ORDER_API("SALES_ORDER_API"),
    SALES_ORDER_MASTER_SAVE("SALES_ORDER_MASTER_SAVE"),
    SALES_ORDER_MASTER_MIGRATION("SALES_ORDER_MASTER_MIGRATION"),
    SALES_ORDER_MASTER("SALES_ORDER_MASTER"),
    SALES_ORDER_SNAPSHOT("SALES_ORDER_SNAPSHOT"),

    RETURN_ORDER_LOCAL("RETURN_ORDER_LOCAL"),
    RETURN_ORDER_API("RETURN_ORDER_API"),
    RETURN_ORDER_MASTER_SAVE("RETURN_ORDER_MASTER_SAVE"),
    RETURN_ORDER_MASTER_MIGRATION("RETURN_ORDER_MASTER_MIGRATION"),
    RETURN_ORDER_MASTER("RETURN_ORDER_MASTER");

    private final String text;
}
