package com.global.order.api.master.config;

import com.global.order.api.common.config.WebMvcConfig;
import com.global.order.core.config.OrderCoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OrderCoreConfig.class, WebMvcConfig.class})
public class OrderMasterApiConfig {
}
