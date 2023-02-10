package com.cloud.main.streaming.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Service;

import com.cloud.main.streaming.StreamingConstants;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RedisPublisher {
	@Autowired
	private ReactiveRedisOperations<String, String> redisOperations;

    public void produceMessageId(String messageId) {
    	produceMessageId(null, messageId);
    }

    public void produceMessageId(String userId, String messageId) {
    	Topic topic;
    	if(StringUtils.isEmpty(userId)) {
    		topic = new ChannelTopic(StreamingConstants.NOTIFICATION_QUEUE_ALL);
    	} else {
    		topic = new ChannelTopic(StreamingConstants.NOTIFICATION_QUEUE_PREFIX + userId);
    	}

    	log.info("Producing {}:{}", topic.getTopic(), messageId);
    	produce(topic, messageId).subscribe(t -> log.info("Redis published for {} subscribers", t));
    }

    private Mono<Long> produce(Topic topic, String message) {
		return redisOperations.convertAndSend(topic.getTopic(), message);
    }
}