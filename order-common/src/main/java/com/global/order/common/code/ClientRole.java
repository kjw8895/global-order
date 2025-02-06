package com.global.order.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientRole implements CodeEnum {
    ROLE_CLIENT("ROLE_CLIENT");

    private final String text;
}
