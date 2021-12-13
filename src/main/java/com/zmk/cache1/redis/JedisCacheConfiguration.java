package com.zmk.cache1.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration
//@EnableCaching
public class JedisCacheConfiguration {
//	  @Bean
//	  JedisConnectionFactory jedisConnectionFactory(){
//	        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("10.72.2.101", 6379);
//	        redisStandaloneConfiguration.setPassword(RedisPassword.of("gssldien"));
//	        return new JedisConnectionFactory(redisStandaloneConfiguration);
//	  }

//	  @Bean
//	  @Primary
//	  RedisTemplate<String, Item> redisTemplate(){
//	    RedisTemplate<String,Item> redisTemplate = new RedisTemplate<String, Item>();
//	    redisTemplate.setConnectionFactory(jedisConnectionFactory());
//	    return redisTemplate;
//	  }
//	  @Bean
//	  public RedisTemplate<String, Object> redisTemplate() {
//	      RedisTemplate<String, Object> template = new RedisTemplate<>();
//	      template.setConnectionFactory(jedisConnectionFactory());
//	      return template;
//	  }
//	  @Bean
//	  public RedisTemplate<String, Country> redisTemplate() {
//	      RedisTemplate<String, Country> template = new RedisTemplate<>();
//	      template.setConnectionFactory(jedisConnectionFactory());
//	      return template;
//	  }
	  
//	    @Bean
//	    public LettuceConnectionFactory redisConnectionFactory() {
//	        // Tạo Standalone Connection tới Redis
//	        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("10.72.2.101", 6379);
//	        redisStandaloneConfiguration.setPassword(RedisPassword.of("gssldien"));
//	        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//	    }
//
//	    @Bean
//	    @Primary
//	    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//	        // tạo ra một RedisTemplate
//	        // Với Key là Object
//	        // Value là Object
//	        // RedisTemplate giúp chúng ta thao tác với Redis
//	        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//	        template.setConnectionFactory(redisConnectionFactory);
//	        return template;
//	    }

}
