package com.vehicle.registry_service.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.registry_service.client.SecurityClient;
import com.vehicle.registry_service.filters.JwtAuthFilter;

@Configuration
public class SecurityConfig {

  private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
  private final SecurityClient securityClient;
  private final ObjectMapper objectMapper;

  public SecurityConfig(SecurityClient securityClient, ObjectMapper objectMapper) {
    this.securityClient = securityClient;
    this.objectMapper = objectMapper;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    JwtAuthFilter jwtFilter = new JwtAuthFilter(securityClient, objectMapper);

    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                "/swagger-resources/**", "/webjars/**")
            .permitAll()
            .requestMatchers("/api/v1/jira-tickets/**").permitAll()
            .requestMatchers("/api/v1/**").permitAll()
            .anyRequest().permitAll())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
