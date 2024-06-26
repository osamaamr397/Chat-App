package com.amr.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//to enable web socket msg broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
           //to add a new stomp and endpoint to our websocket configuration
        //if i want to make it with https the pattern will be wss
        // /ws which mean the path web socket
        registry.addEndpoint("/ws").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         *the point where we want to enable simple broker or to enable the application destination
         * prefixes
         */
        registry.setApplicationDestinationPrefixes("/app");
        //it takes a destination prefixes and in our case will be /topic
        registry.enableSimpleBroker("/topic");

    }

}
