package com.global.order.worker.config;

import com.global.order.client.kafka.config.property.KafkaTopicProperties;
import com.global.order.common.code.MessageCategory;
import com.global.order.common.code.RegionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * profile 마다 topic이 다르기 때문에 Bean 등록 후 사용
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaTopicProperties.class})
public class KafkaListenerTopicConfig {

    @Bean
    public String salesOrderLocalKrTopic(KafkaTopicProperties kafkaTopicProperties) {
        return kafkaTopicProperties.getName(MessageCategory.ORDER_LOCAL, RegionCode.KR);
    }

    @Bean
    public String salesOrderApiKrTopic(KafkaTopicProperties kafkaTopicProperties) {
        return kafkaTopicProperties.getName(MessageCategory.ORDER_API, RegionCode.KR);
    }

    @Bean
    public String salesOrderCrudTopic(KafkaTopicProperties kafkaTopicProperties) {
        return kafkaTopicProperties.getName(MessageCategory.ORDER_CRUD);
    }
}
