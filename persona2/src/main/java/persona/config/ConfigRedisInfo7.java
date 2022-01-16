//package persona.config;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//@Configuration
//public class ConfigRedisInfo7 {
//
//	@Value("${micro-eis.redis.hostname}")
//	private String hostname;
//
//	@Value("${micro-eis.redis.port}")
//	private int port;
//	
//	@Value("${micro-eis.redis.apply}")
//	private boolean applyRedis;
//
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName(hostname);
//		redisStandaloneConfiguration.setPort(port);
//		return new JedisConnectionFactory(redisStandaloneConfiguration);
//	}
//
//	@Bean
//	public RedisTemplate<String,List<Object>> queryTemplate() {
//		RedisTemplate<String, List<Object>> redisTemplate = new RedisTemplate<String, List<Object>>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		redisTemplate.setExposeConnection(true);
//		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//		return redisTemplate;
//	}
//
//	@Bean
//	public RedisCacheConfiguration cacheConfiguration() {
//		return RedisCacheConfiguration.defaultCacheConfig();
//	}
//
//	@Bean
//	public CacheManager cacheManager() {
//		if(applyRedis) {
//			return RedisCacheManager.builder(jedisConnectionFactory()).cacheDefaults(cacheConfiguration())
//					.transactionAware().build();
//		} else {
//			return new ConcurrentMapCacheManager();
//		}
//		
//	}
//
//}