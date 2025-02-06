package com.global.order.common.code;

import org.springframework.http.HttpStatus;

public interface ExceptionCodeEnum {
    int getCode();
    String getMsg();
    HttpStatus getHttpStatus();
}
