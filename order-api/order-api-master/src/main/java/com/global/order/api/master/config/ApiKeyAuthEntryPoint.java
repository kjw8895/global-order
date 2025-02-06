package com.global.order.api.master.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.constant.HttpConstant;
import com.global.order.common.response.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiKeyAuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        CommonExceptionCode code = request.getHeader(HttpConstant.X_API_KEY) == null ? CommonExceptionCode.MISSING_API_KEY : CommonExceptionCode.INVALID_API_KEY;
        ResponseEntity<CommonResponse<Object>> errorResponse = CommonResponse.error(code);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}
