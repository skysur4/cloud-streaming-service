package com.cloud.main.streaming.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;


@Configuration
@EnableRedisWebSession(maxInactiveIntervalInSeconds = 1800)
public class StreamingSessionConfig {
}