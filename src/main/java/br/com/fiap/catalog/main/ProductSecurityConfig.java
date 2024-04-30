package br.com.fiap.catalog.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SuppressWarnings(value = "all")
public class ProductSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, CustomBasicAuthFilter customBasicAuthFilter) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers(HttpMethod.GET,"/v1/product/category").permitAll();
                    authorizeConfig.requestMatchers(HttpMethod.GET,"/v1/product/all").permitAll();
                    authorizeConfig.requestMatchers(HttpMethod.GET,"/v1/product").permitAll();
                    authorizeConfig.requestMatchers(HttpMethod.GET,"/{id}").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                    }
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(customBasicAuthFilter, BasicAuthenticationFilter.class)
                .csrf().disable();

        return httpSecurity.build();
    }
}
