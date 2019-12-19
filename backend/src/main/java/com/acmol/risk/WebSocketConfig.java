package com.acmol.risk;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Class used for the WebServer endpoint configuration.
 *
 * Please see the {@link com.acmol.risk.ServerController} to see the aforementioned endpoint.
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
	} 
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic","/game");
	}

	

}