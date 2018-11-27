package com.alexander.springsocial.chat;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * @author Alexander Hartson
 */
// tag::intro
@Configuration
public class WebSocketConfig {
// end::intro

    // tag::cloud-1
    @Profile("cloud")
    @Configuration
    @EnableConfigurationProperties(ChatConfigProperties.class)
    static class CloudBasedWebSocketConfig {
        // end::cloud-1

        private static final Logger log = LoggerFactory.getLogger(CloudBasedWebSocketConfig.class);

        // tag::cloud-2
        private final ChatConfigProperties chatConfigProperties;

        CloudBasedWebSocketConfig(ChatConfigProperties chatConfigProperties) {
            this.chatConfigProperties = chatConfigProperties;
        }
        // end::cloud-2

        // tag::cors
        @Bean
        HandlerMapping webSocketMapping(CommentService commentService,
                                        InboundChatService inboundChatService,
                                        OutboundChatService outboundChatService) {
            SimpleUrlHandlerMapping mapping =
                    configureUrlMappings(commentService, inboundChatService, outboundChatService);

            Map<String, CorsConfiguration> corsConfigurationMap = new HashMap<>();
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedOrigin(chatConfigProperties.getOrigin());

            mapping.getUrlMap().keySet().forEach(route ->
                    corsConfigurationMap.put(route, corsConfiguration)
            );

            mapping.setCorsConfigurations(corsConfigurationMap);

            return mapping;
        }
        // end::cors
    }

    // tag::websocket-non-cloud
    @Profile("!cloud")
    @Configuration
    static class LocalWebSocketConfig {
        // end::websocket-non-cloud

        // tag::non-cloud-configure-inbound
        @Bean
        HandlerMapping webSocketMapping(CommentService commentService,
                                        InboundChatService inboundChatService,
                                        OutboundChatService outboundChatService) {
            return configureUrlMappings(commentService, inboundChatService, outboundChatService);
        }
        // end::non-cloud-configure-inbound
    }

    @Bean
    WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    // tag::url-mappings
    private static SimpleUrlHandlerMapping configureUrlMappings(CommentService commentService,
                                                                InboundChatService inboundChatService,
                                                                OutboundChatService outboundChatService) {
        Map<String, WebSocketHandler> urlMap = new HashMap<>();
        urlMap.put("/topic/comments.new", commentService);
        urlMap.put("/app/chatMessage.new", inboundChatService);
        urlMap.put("/topic/chatMessage.new", outboundChatService);

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(10);
        mapping.setUrlMap(urlMap);

        return mapping;
    }
    // end::url-mappings
}
