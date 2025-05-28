package com.aleatest.pokemonapi.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration class that enables redis based caching for the application.
 * Defines the {@link RedisCacheConfiguration} bean used by Spring Cache.
 * It sets a default time-to-live (TTL) for cached entries and specifies serializers 
 * for cache keys and values.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {
    
    /**
     * Creates a customized {@link RedisCacheConfiguration} bean.
     * Keys are serialized as plain strings using {@link StringRedisSerializer},
     * values are serialized as JSON using {@link GenericJackson2JsonRedisSerializer} and
     * each cache entry will expire after 12 hours.
     * @return a configured {@link RedisCacheConfiguration} instance for use by Spring's cache manager
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisSerializer<String> keySerializer   = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new GenericJackson2JsonRedisSerializer();

        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer))
            .entryTtl(Duration.ofSeconds(43200));
    }
}
