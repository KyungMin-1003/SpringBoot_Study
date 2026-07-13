package com.example.springboot.springboot.global.config;

import com.example.springboot.springboot.global.security.handler.CustomAccessDeniedHandler;
import com.example.springboot.springboot.global.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


//접근 규칙과 로그인 설정
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomAuthenticationEntryPoint authenticationEntryPoint,
            CustomAccessDeniedHandler accessDeniedHandler
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/members/signup",
                                "/members/login",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/error"
                        ).permitAll()

                        .requestMatchers(
                                PathRequest.toH2Console()
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginProcessingUrl("/members/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )

                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}
