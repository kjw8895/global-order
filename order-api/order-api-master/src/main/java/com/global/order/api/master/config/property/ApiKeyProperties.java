package com.global.order.api.master.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("api")
public class ApiKeyProperties {
    private String key;
}
