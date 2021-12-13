package com.zmk.cache1.config;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.google.common.cache.CacheBuilder;
import com.zmk.cache1.helper.GlobalVariable;

@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Tạo Standalone Connection tới Redis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("10.72.2.101", 6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("gssldien"));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // tạo ra một RedisTemplate
        // Với Key là Object
        // Value là Object
        // RedisTemplate giúp chúng ta thao tác với Redis
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
//    @Primary
//	  @Bean
//	  public CacheManager cacheManager() {
//	    RedisCacheManager redisCacheManager = RedisCacheManager.create(redisConnectionFactory());
//	    return redisCacheManager;
//	  }
    @Primary
    @Bean
    public RedisCacheManager redisCacheManager() {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .disableCachingNullValues()
//                .entryTtl(Duration.ofSeconds(20))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
//        redisCacheConfiguration.usePrefix();

//       return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory())
//                        .cacheDefaults(redisCacheConfiguration).build();
        RedisCacheConfiguration redisCacheConfiguration1 = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(60*10))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        redisCacheConfiguration1.usePrefix();
        RedisCacheConfiguration redisCacheConfiguration2 = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(60*10))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        redisCacheConfiguration2.usePrefix();
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        configurationMap.put(GlobalVariable.CACHE_ENTITY_COUNTRY, redisCacheConfiguration1);  
        configurationMap.put(GlobalVariable.CACHE_ENTITY_COUNTRY2, redisCacheConfiguration2);    
        
        //RedisCacheManager.builder().withInitialCacheConfigurations(configurationMap);
        
		//return RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory()).build();
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory()).withInitialCacheConfigurations(configurationMap).build();

    }  
//    @Bean
//    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//        return (builder) -> {
//            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
//            configurationMap.put("cache1", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));  
//            configurationMap.put("cache2", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(2)));     
//            builder.withInitialCacheConfigurations(configurationMap);
//            RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());
//        };
//    }

//    @Bean
//    public CacheManager alternateCacheManager() {
//        return new ConcurrentMapCacheManager("customerOrders", "orderprice");
//    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("addresses");
//    }
    @Bean(name = "cacheManagerMemX")
    public CacheManager cacheManager3() {
     ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

      @Override
      protected Cache createConcurrentMapCache(final String name) {
       return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES)
         .maximumSize(1).build().asMap(), true);
      }
     };

     cacheManager.setCacheNames(Arrays.asList(GlobalVariable.CACHE_ENTITY_COUNTRY, GlobalVariable.CACHE_ENTITY_COUNTRY2));
     
     return cacheManager;
    }
//    @Bean(name = "cacheManagerMemX")
//    public CacheManager cacheManagerMem() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(
//          new ConcurrentMapCache(GlobalVariable.CACHE_ENTITY_COUNTRY), 
//          new ConcurrentMapCache("addresses")));
//        return cacheManager;
//    }
}