package com.cloud.main.streaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Service;

import com.cloud.main.streaming.StreamingConstants;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class RedisSubscriber {

	@Autowired
	ReactiveRedisOperations<String, String> redisOperations;

    public Flux<String> comsume(String userId) {
		Topic commonTopic = new ChannelTopic(StreamingConstants.NOTIFICATION_QUEUE_ALL);
		Topic personalTopic = new ChannelTopic(StreamingConstants.NOTIFICATION_QUEUE_PREFIX + userId);

		return redisOperations.listenTo(personalTopic, commonTopic)
				.map(message -> {
					log.info("Consuming {}:{}", message.getChannel(), message.getMessage());

					return message.getMessage();
				});
    }
}