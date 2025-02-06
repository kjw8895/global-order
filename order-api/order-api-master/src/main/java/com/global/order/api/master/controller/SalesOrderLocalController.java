package com.global.order.api.master.controller;

import com.global.order.api.master.facade.SalesOrderLocalFacade;
import com.global.order.common.application.dto.SalesOrderLocalDto;
import com.global.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 원래는 각 국가 서버에서 구현 할 api, 테스트 목적으로 생성
 */
@Deprecated
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/local/order")
public class SalesOrderLocalController {
    private final SalesOrderLocalFacade facade;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<SalesOrderLocalDto>> findById(@PathVariable Long id) {
        return CommonResponse.ok(facade.findById(id));
    }
}
