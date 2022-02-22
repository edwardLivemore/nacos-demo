package com.edward.nacosgateway.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class GatewayService {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void updateRoutes(List<RouteDefinition> routes) {
        if(CollectionUtils.isEmpty(routes)) {
            log.info("No Routes Found");
            return;
        }

        routes.forEach(r -> {
            try {
                routeDefinitionWriter.save(Mono.just(r)).subscribe();
                applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            } catch (Exception e) {
                log.error("cannot update route, id={}", r.getId());
            }
        });
    }
}
