package com.wordwise.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
* @Configuration : 해당 클래스가 Spring 설정파일임을 의미
* @EnableCaching : Spring의 캐싱 기능을 활성화함 (Spring Boot에서 Redis를 캐시 저장소(Cache Storage)로 사용할 수 있도록함)
* */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);   // Redis 연결 위한 기본 팩토리를 Lettuce로 지정
        template.setKeySerializer(new StringRedisSerializer()); // Key를 문자열로 저장
        template.setValueSerializer(new StringRedisSerializer());   // Value를 문자열로 저장
        return template;
    }
}
