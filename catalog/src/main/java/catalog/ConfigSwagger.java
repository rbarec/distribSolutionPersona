package catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigSwagger {
	
	final static String BASE_PACKAGE = "catalog.api";
	final static  String VERSION="1.0.0";
	final static  String APP_NAME="Catalogos";
	

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(ConfigSwagger.BASE_PACKAGE))
				//.paths(PathSelectors.regex("/*") )//
//				.paths(PathSelectors.regex("/admin/api") )//
				.build().apiInfo(apiEndPointsInfo());
	}
	

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder()//
				.description("distribuidos Proyecto")
				.title(ConfigSwagger.APP_NAME+ " REST de Servicios")
				.contact(new Contact("INFO", "espol", "fiec"))
				.version(ConfigSwagger.VERSION)//
				.build();
	}
	

}