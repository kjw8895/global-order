package com.global.order.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegionCode implements CodeEnum {
    KR("KR"),
    TW("TW"),
    US("US"),
    HK("HK");

    private final String text;
}
