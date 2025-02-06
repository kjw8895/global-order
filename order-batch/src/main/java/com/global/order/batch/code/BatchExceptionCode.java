package com.global.order.batch.code;

import com.global.order.common.code.ExceptionCodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BatchExceptionCode implements ExceptionCodeEnum {
    EMPTY_MESSAGE(6001, "Message is empty", HttpStatus.BAD_REQUEST),
    MESSAGE_TRANSMISSION_FAILED(6002, "Message transmission failed", HttpStatus.INTERNAL_SERVER_ERROR),
    POLLING_FAILED(6003, "Message polling failed", HttpStatus.INTERNAL_SERVER_ERROR),
    UNSUPPORTED_EVENT_CATEGORY(6004, "Unsupported event category", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String msg;
    private HttpStatus httpStatus;

    BatchExceptionCode(int code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
