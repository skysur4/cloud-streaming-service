package com.cloud.main.streaming.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.main.streaming.StreamingConstants;
import com.cloud.main.streaming.service.RedisPublisher;

/**
 * Streaming internal 기능
 *
 * @author chris.kim
 * @since 2022-12-08
 * @version 1.0
*/

@RestController
@RequestMapping("/internal" + StreamingConstants.VERSION_1)
public class InternalController {
	@Autowired
	RedisPublisher redisPublisher;

    @PostMapping("/publish/notification")
    public Map<String, List<String>> publishNotification(@RequestBody Map<String, List<String>> message) {

    	message.forEach((messageId, userList) -> {
    		userList.forEach(userId -> redisPublisher.produceMessageId(userId, messageId));

    	});

    	return message;
    }
}
