package com.global.order.client.kafka.config.property;

import com.global.order.common.code.MessageCategory;
import com.global.order.common.code.RegionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTopic {
    private MessageCategory category;
    private RegionCode regionCode;
    private String name;
}
