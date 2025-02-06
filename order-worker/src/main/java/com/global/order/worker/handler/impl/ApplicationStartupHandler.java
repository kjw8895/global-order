package com.global.order.worker.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartupHandler implements SmartLifecycle {
    private Boolean isRunning = false;

    /**
     * Spring 컨테이너가 완전히 초기화된 후, 모든 빈이 생성 및 초기화된 직후 실행
     * 서비스 초기화 작업을 비동기적으로 실행하거나, 컨테이너 시작 후 수행해야 할 로직을 넣을 때 사용
     */
    public void onStartup() {
        log.info("ApplicationStartupHandler onStartup");
    }

    @Override
    public void start() {
        onStartup();
        this.isRunning = true;
    }

    @Override
    public void stop() {
        this.isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public int getPhase() {
        return Integer.MIN_VALUE;
    }
}
