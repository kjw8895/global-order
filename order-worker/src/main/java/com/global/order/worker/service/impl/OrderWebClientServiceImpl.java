package com.global.order.worker.service.impl;

import com.global.order.client.web.config.property.WebClientUrlProperties;
import com.global.order.client.web.service.WebClientService;
import com.global.order.common.application.dto.SalesOrderLocalDto;
import com.global.order.common.code.RegionCode;
import com.global.order.common.constant.HttpConstant;
import com.global.order.common.exception.CommonException;
import com.global.order.common.response.CommonResponse;
import com.global.order.common.utils.ObjectMapperUtils;
import com.global.order.worker.exception.WorkerExceptionCode;
import com.global.order.worker.service.OrderWebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({WebClientUrlProperties.class})
public class OrderWebClientServiceImpl implements OrderWebClientService {
    private final WebClientService webClientService;
    private final WebClientUrlProperties webClientUrlProperties;

    @Override
    public SalesOrderLocalDto findOrderByOrderId(Long id, RegionCode regionCode) {
        try {
            WebClientUrlProperties.Client client = webClientUrlProperties.getClient(regionCode);

            Map<String, String> headers = new HashMap<>();
            headers.put(HttpConstant.X_CLIENT_ID, client.getClientId());
            headers.put(HttpConstant.X_API_KEY, client.getClientId());

            String orderUrl = client.getUrl().getWithPathVariable(client.getUrl().getOrder(), id);
            CommonResponse<?> response = (CommonResponse<?>) webClientService.get(orderUrl, headers, null, CommonResponse.class);
            Object data = response.getData();

            log.info("api response data : {}", response.getData());

            SalesOrderLocalDto result = ObjectMapperUtils.convertTreeToValue(data, SalesOrderLocalDto.class);

            if (result == null) {
                throw new CommonException(WorkerExceptionCode.NOT_FOUND_LOCAL_RESOURCE);
            }

            return result;
        } catch (Exception e) {
            log.error("error : not found local resource - id : {}, regionCode : {}", id, regionCode);
            log.error("error : find sales order failed", e);
            throw e;
        }
    }
}
