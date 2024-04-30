package br.com.fiap.catalog.main;

import br.com.fiap.catalog.infrastructure.controllers.mappers.ResponseEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    ResponseEntityMapper responseEntityMapper() {
        return new ResponseEntityMapper();
    }
}
