package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class BasicAuthenticationSecurityConfiguration {


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    return http.authorizeHttpRequests(
            auth -> auth
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated() // 모든 요청에 인증을 요구
        )
        .httpBasic(Customizer.withDefaults()) // HTTP 기본인증 (alert 창 로그인)
        .sessionManagement(
            session -> session.sessionCreationPolicy
                (SessionCreationPolicy.STATELESS) // 세션 생성 정책을 "무 상태"로 설정
        )
        .csrf().disable() // CSRF 보호를 비활성화
        .build();
  }


}
