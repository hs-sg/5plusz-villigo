package com.splusz.villigo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
				
		registry.addEndpoint("/ws") // -> 클라이언트는 이 경로를 통해 서버와 연결을 함  
				.setAllowedOrigins("*") // -> 모든 origin에서의 연결을 허용, 실제 운영환경에서는 특정 오리진만 허용하도록 해야함
				.withSockJS(); // -> 모든 브라우저에서 채팅 기능을 사용할 수 있도록 호환성을 높임
	}
	
	@Override
	// 메시지 브로커 설정, 메시지 라우팅, 구독, 발행 등을 정의
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 클라이언트가 구독할 주제 prefix
		registry.enableSimpleBroker("/topic", "/queue"); // /topic은 1:N 메시지 전송, /queue는 1:1 메시지 전송에 사용
		// 서버로 메시지를 보낼때 prefix
		registry.setApplicationDestinationPrefixes("/app");// 서버의 특정 핸들러로 메시지를 보내기 위한 경로
		// 1:1 메시지를 위한 prefix
		registry.setUserDestinationPrefix("/user"); // 1:1 메시지를 위한 경로 접두사로 user 사용
	}
	
	

}
