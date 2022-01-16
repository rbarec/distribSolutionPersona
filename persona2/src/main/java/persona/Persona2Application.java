package persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class Persona2Application {

	public static void main(String[] args) {
		SpringApplication.run(Persona2Application.class, args);
	}

	@Bean 
	public RestTemplate restTemplate(RestTemplateBuilder builder){
	return builder.build();
	}
	
	
	@Bean
	public UndertowServletWebServerFactory undertowServletWebServerFactory() {
		UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
	    return factory;
	}
	
	
//	
//	@Bean
//	public RedisCacheConfiguration cacheConfiguration() {
//	    return RedisCacheConfiguration.defaultCacheConfig()
//	      .entryTtl(Duration.ofMinutes(60))
//	      .disableCachingNullValues()
//	      .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//	}
//	
//	
//	 @Bean
//   public KeyGenerator KeyGenerator() {
//       return new KeyGenerator() {
//           @Override
//           public Object generate(Object target, Method method, Object... params) {
//               StringBuilder sb = new StringBuilder();
//               sb.append(target.getClass().getName());
//               sb.append(method.getName());
//               for (Object obj : params) {
//                   sb.append(obj.toString());
//               }
//               return sb.toString();
//           }
//       };
//   }
}
