package br.com.henriquemonteiro.ordemdeservicos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todas as URLs da API
                        .allowedOrigins("https://ordemdeservico-desafio-5.onrender.com") // Permitir o domínio do frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Permitir todos os headers
                        .allowCredentials(true); // Permite envio de credenciais (se necessário)
            }
        };
    }
}

