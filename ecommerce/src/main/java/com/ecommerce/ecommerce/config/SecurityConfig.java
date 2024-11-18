package com.ecommerce.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.ecommerce.exception.CustomAccessDeniedException;
import com.ecommerce.ecommerce.exception.CustomUnAuthorizedException;
import com.ecommerce.ecommerce.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
        JwtFilter filter;

        @Bean
        PasswordEncoder getPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf.disable())
                        .sessionManagement(session -> session
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .exceptionHandling(ex -> ex
                                        .authenticationEntryPoint(new CustomUnAuthorizedException())
                                        .accessDeniedHandler(new CustomAccessDeniedException()))
                        .authorizeHttpRequests(auth -> auth
                                        .requestMatchers("/auth/**",
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**",
                                                "/home/**",
                                                "/product/photos/*")
                                        .permitAll()
                                        .anyRequest().authenticated())
                        .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
