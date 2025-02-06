package com.global.order.client.kafka.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kafka.producer")
public class KafkaProducerProperties {
    private String bootstrapServers;
}
