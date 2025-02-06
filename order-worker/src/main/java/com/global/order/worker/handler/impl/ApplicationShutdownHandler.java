package com.global.order.worker.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationShutdownHandler implements SmartLifecycle {
    private Boolean isRunning = false;

    /**
     * Spring 컨텍스트가 종료될 때
     * 리소스를 정리 하거나 연결 종료 등의 작업이 필요할 때
     */
    public void onShutdown() {
        log.info("ApplicationStartupHandler onShutdown");
    }

    @Override
    public void start() {
        this.isRunning = true;
    }

    @Override
    public void stop() {
        onShutdown();
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
