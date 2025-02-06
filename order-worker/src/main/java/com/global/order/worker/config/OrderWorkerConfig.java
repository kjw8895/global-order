package com.global.order.worker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.order.client.kafka.config.KafkaConfig;
import com.global.order.common.config.module.CommonObjectMapperFactory;
import com.global.order.core.config.OrderCoreConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OrderCoreConfig.class, KafkaConfig.class})
@ComponentScan(value = {
        "com.global.order.client.web",
        "com.global.order.client.kafka",
        "com.global.order.client.s3"
})
@RequiredArgsConstructor
public class OrderWorkerConfig {
    @Bean
    ObjectMapper objectMapper() {
        return CommonObjectMapperFactory.defaultObjectMapper();
    }
}
