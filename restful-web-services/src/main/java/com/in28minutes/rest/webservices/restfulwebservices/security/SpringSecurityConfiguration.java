package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 모든 요청이 인증되도록 한다.
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        //요청이 인증되지 않으면 웹페이지가 표시된다.
        http.httpBasic(Customizer.withDefaults());

        // CSRF 해제 -> POST, PUT
        http.csrf().disable();
        return http.build();
    }

}
