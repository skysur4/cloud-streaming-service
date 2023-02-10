package com.cloud.main.streaming.controller;

import javax.xml.ws.http.HTTPException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.main.streaming.StreamingConstants;
import com.cloud.main.streaming.service.RedisSubscriber;

import reactor.core.publisher.Flux;

/**
 * Streaming console 기능
 *
 * @author chris.kim
 * @since 2022-12-08
 * @version 1.0
*/

@RestController
@RequestMapping("/console" + StreamingConstants.VERSION_1)
public class ConsoleController {
	@Autowired
	RedisSubscriber redisSubscriber;

	@GetMapping(value = "/connection/notification")
	public Flux<ServerSentEvent<String>> connectNotification(OAuth2AuthenticationToken token) {
		if(!token.isAuthenticated()) {
			throw new HTTPException(HttpStatus.FORBIDDEN.value());
		}

		OAuth2User user = token.getPrincipal();
		String userId = user.getAttribute("user_uid").toString();

		if(StringUtils.isEmpty(userId)) {
			throw new HTTPException(HttpStatus.FORBIDDEN.value());
		}

		return redisSubscriber.comsume(userId)
				.map(messageId -> ServerSentEvent.builder(messageId).build());
	}
}