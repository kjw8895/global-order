package com.global.order.client.web.config.property;

import com.global.order.common.code.CommonExceptionCode;
import com.global.order.common.code.RegionCode;
import com.global.order.common.exception.CommonException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("web-client")
public class WebClientUrlProperties {
    private Client hk;
    private Client tw;
    private Client us;
    private Client kr;

    public Client getClient(RegionCode regionCode) {
        switch (regionCode) {
            case HK -> {
                return this.hk;
            }
            case TW -> {
                return this.tw;
            }
            case US -> {
                return this.us;
            }
            case KR -> {
                return this.kr;
            }
        }
        throw new CommonException(CommonExceptionCode.INVALID_REQUEST);
    }

    @Getter
    @Setter
    public static class Client {
        private String clientId;
        private Url url;
    }

    @Getter
    @Setter
    public static class Url {
        String order;

        public String getWithPathVariable(String url, Object path) {
            return String.format("%s/%s", url, path);
        }
    }
}
