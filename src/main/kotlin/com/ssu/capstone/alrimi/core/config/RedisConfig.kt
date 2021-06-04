package com.ssu.capstone.alrimi.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.data.redis.serializer.GenericToStringSerializer







@Configuration
@EnableRedisRepositories
class RedisConfig(
    @Value("\${spring.redis.port}")
    val port: Int,

    @Value("\${spring.redis.host}")
    val host: String
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Double> {
        val redisTemplate: RedisTemplate<String, Double> = RedisTemplate()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.setValueSerializer(GenericToStringSerializer(Double::class.java))
        redisTemplate.setConnectionFactory(redisConnectionFactory())

        return redisTemplate
    }
}