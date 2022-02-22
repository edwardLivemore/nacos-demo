package com.edward.nacosgateway.dynamic;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class DynamicRoutesListener implements Listener {
    @Autowired
    private GatewayService gatewayService;

    @Override
    public Executor getExecutor() {
        log.info("getExecutor");
        return null;
    }

    // 使用JSON转换，将plain text变为RouteDefinition
    @Override
    public void receiveConfigInfo(String config) {
        log.info("receive route changes: {}", config);

        List<RouteDefinition> routeDefinitions = JSON.parseArray(config, RouteDefinition.class);
        gatewayService.updateRoutes(routeDefinitions);
    }
}
