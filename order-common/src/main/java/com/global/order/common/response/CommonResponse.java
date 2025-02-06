package com.global.order.common.response;

import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.exception.CommonException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CommonResponse<T> {
    private T data;
    private ResponseMetadata metadata;

    public static <T> ResponseEntity<CommonResponse<T>> of(final HttpStatus status, final T data, final ResponseMetadata metadata) {
        return ResponseEntity.status(status).body(new CommonResponse<>(data, metadata));
    }

    public static <T> ResponseEntity<CommonResponse<T>> ok(final T data) {
        return of(HttpStatus.OK, data, ResponseMetadata.ok());
    }

    public static <T> ResponseEntity<CommonResponse<T>> error(CommonException e) {
        return of(e.getHttpStatus(), null, ResponseMetadata.of(e.getCode(), e.getMessage()));
    }

    public static <T> ResponseEntity<CommonResponse<T>> error(CommonExceptionCode code) {
        return of(code.getHttpStatus(), null, ResponseMetadata.of(code.getCode(), code.getMsg()));
    }
}
