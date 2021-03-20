package com.example.appcommon.config;

import com.example.appcommon.enums.CacheEnum;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.EnumSet;
import java.util.stream.Collectors;

/**
 * 缓存配置
 *
 * @Author: wcg
 * @Date: 2021/3/1 23:40
 **/
@EnableCaching
@Configuration
public class RedisCacheConfig {
    
    /**
     * 默认情况下会有两个模板类被注入Spring IoC供我们使用:
     *      RedisTemplate: 主要用于对象缓存，其默认使用JDK序列化，需要更改其序列化方式解决一些问题，比如Java 8日期问题、JSON序列化问题。
     *      StringRedisTemplate: 主要处理键值都是字符串的缓存，采用默认就好.
     *
     * [redisConnectionFactory]
     * @Return org.springframework.data.redis.core.RedisTemplate<java.lang.Object,java.lang.Object>
     * @Author: wcg
     * @Time: 2021/3/2 22:24
     */
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jsonRedisSerializer = initJacksonSerializer();
        // 设置key的序列化规则和value的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    
    
    /**
     * 处理redis序列化问题
     * @Return org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
     * @Author: wcg
     * @Time: 2021/3/2 22:24
     */
    private Jackson2JsonRedisSerializer initJacksonSerializer() {
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //替代旧版本 om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objMapper.activateDefaultTyping(objMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        //bugFix Jackson2反序列化数据处理LocalDateTime类型时出错
        objMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        // java8 时间支持
        objMapper.registerModule(new JavaTimeModule());
        jsonRedisSerializer.setObjectMapper(objMapper);
        return jsonRedisSerializer;
    }
    
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(RedisTemplate<Object, Object> redisTemplate, CacheProperties cacheProperties){
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        //缓存序列化处理
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
        
        // 全局 TTL
        if(redisProperties.getTimeToLive() != null){
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(redisProperties.getTimeToLive());
        }
        
        // key 前缀
        if(redisProperties.getKeyPrefix() != null){
            redisCacheConfiguration = redisCacheConfiguration.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        
        
        // 默认缓存 null 值，可防止缓存穿透
        if(!redisProperties.isCacheNullValues()){
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }
        
        // 不使用 key 前缀
        if(!redisProperties.isUseKeyPrefix()){
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        }
        
        return redisCacheConfiguration;
    }
    
    
    /**
     * 个性化配置缓存过期时间
     * [redisCacheConfiguration]
     * @Return org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
     * @Author: wcg
     * @Time: 2021/3/2 22:51
     */
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(RedisCacheConfiguration redisCacheConfiguration){
        return builder -> builder.cacheDefaults(redisCacheConfiguration)
                //自定义一些缓存配置初始化，主要是特定缓存及其ttl
                .withInitialCacheConfigurations(EnumSet.allOf(CacheEnum.class).stream()
                        .collect(Collectors.toMap(CacheEnum::cacheName,
                                cacheEnum -> redisCacheConfiguration.entryTtl(Duration.ofSeconds(cacheEnum.ttlSecond())))));
    }
    
}
