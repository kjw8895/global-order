package com.global.order.client.kafka.config.property;

import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.code.MessageCategory;
import com.global.order.common.code.RegionCode;
import com.global.order.common.exception.CommonException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties("kafka")
public class KafkaTopicProperties {
    private List<KafkaTopic> topic;

    public String getName(MessageCategory category) {
        return this.topic.stream()
                .filter(item -> item.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new CommonException(CommonExceptionCode.UNKNOWN_SEVER_ERROR))
                .getName();
    }

    public String getName(MessageCategory category, RegionCode regionCode) {
        return this.topic.stream()
                .filter(item -> item.getRegionCode() != null)
                .filter(item -> item.getRegionCode().equals(regionCode) && item.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new CommonException(CommonExceptionCode.UNKNOWN_SEVER_ERROR))
                .getName();
    }
}
