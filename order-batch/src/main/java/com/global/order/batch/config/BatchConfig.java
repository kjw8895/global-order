package com.global.order.batch.config;

import com.global.order.client.kafka.config.KafkaConfig;
import com.global.order.core.config.OrderCoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({OrderCoreConfig.class, KafkaConfig.class})
@EnableConfigurationProperties(BatchProperties.class)
@ComponentScan(value = {
        "com.global.order.client.kafka",
})
public class BatchConfig {
}
