package br.com.henriquemonteiro.OrdemDeServicos.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

		@Bean 
		//Usado para agrupar os endpoints e gerar a documentação
		public GroupedOpenApi publicApi() {
			return GroupedOpenApi
			.builder()
			.group("api")
			.pathsToMatch("/**")// Define os endpoints que serão documentados
			.build();
		}
	
}
