package com.global.order.api.master.controller;

import com.global.order.api.master.facade.SalesOrderMasterFacade;
import com.global.order.common.code.RegionCode;
import com.global.order.common.response.CommonResponse;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.application.dto.request.SalesOrderMasterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order/master")
@RequiredArgsConstructor
public class SalesOrderMasterController {
    private final SalesOrderMasterFacade facade;

    @GetMapping
    public ResponseEntity<CommonResponse<SalesOrderMasterEntityDto>> findOne(@RequestParam Long salesOrderId, @RequestParam RegionCode regionCode) {
        return CommonResponse.ok(facade.findOne(salesOrderId, regionCode));
    }

    @GetMapping("/page")
    public ResponseEntity<CommonResponse<Page<SalesOrderMasterEntityDto>>> page(Pageable pageable, SalesOrderMasterRequestDto request) {
        return CommonResponse.ok(facade.page(pageable, request));
    }
}
