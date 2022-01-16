//package persona.config;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@Configuration
//@EnableCaching
//public class ConfigCacheChannelInfo7 extends CachingConfigurerSupport {
//
//
//	
//	@Override
//	@Bean("metadataCacheManager")
//	@Primary
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager();
//	}
//
//	@Override
//	public CacheErrorHandler errorHandler() {
//		return new CustomCacheErrorHandler();
//	}
//	
//////	@Bean
////	public ConnectionFactory cacheConnectionFactory() {
////		log.info("\n >>> CHANNEL_QUEUE  cacheConnectionFactory");
////		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
////		connectionFactory.setAddresses(this.address);
////		connectionFactory.setUsername(this.username);
////		connectionFactory.setPassword(this.password);
////		connectionFactory.setPublisherReturns(true);
////		connectionFactory.setPublisherConfirms(true);
////		connectionFactory.setVirtualHost(this.virtualHost);
////		return connectionFactory;
////	}
//    
//////	@Bean
////	public AmqpAdmin amqpAdminForCachex() throws UnknownHostException {
////		log.info("\n >>> CHANNEL_QUEUE  amqpAdminForCache");
////	    RabbitAdmin admin = new RabbitAdmin(cacheConnectionFactory());
////		String queueName =  this.applicationName + ".queue." + InetAddress.getLocalHost().getHostName();
////		log.info("\n\n\n QUEUE-NAME: "+queueName+"  \n\n\n");
////	    Queue queue = new Queue(queueName, false, false, true);
////	    Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, this.exchange, "", null);
////	    admin.declareQueue(queue);
////	    admin.declareBinding(binding);
////	    return admin;
////	}
//	
//////    @Bean
////    public SimpleRabbitListenerContainerFactory cacheListenerContainerFactoryx() {
////    	log.info("\n >>> CHANNEL_QUEUE  SimpleRabbitListenerContainerFactory");
////        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
////        factory.setConnectionFactory(cacheConnectionFactory());
////        factory.setConcurrentConsumers(3);
////        factory.setMaxConcurrentConsumers(10);
////        return factory;
////    }
////    
////    
//    
//    
//    
//    
//    
//    /**
//     * 
//     * @author ronnb
//     *
//     */
//    public static class CustomCacheErrorHandler implements CacheErrorHandler {
//    	
//    	private static final Logger LOGGER = LogManager.getLogger(CustomCacheErrorHandler.class);
//    	
//    	@Override
//    	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
//    		LOGGER.error("Cache Get Error: " + exception.getMessage());
//    	}
//
//    	@Override
//    	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
//    		LOGGER.error("Cache Put Error: " + exception.getMessage());
//    	}
//
//    	@Override
//    	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
//    		LOGGER.error("Cache Evict Error: " + exception.getMessage());
//    	}
//
//    	@Override
//    	public void handleCacheClearError(RuntimeException exception, Cache cache) {
//    		LOGGER.error("Cache Clear Error: " + exception.getMessage());
//    	} 
//    }
//}
