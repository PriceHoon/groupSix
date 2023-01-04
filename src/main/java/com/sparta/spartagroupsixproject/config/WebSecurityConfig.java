package com.sparta.spartagroupsixproject.config;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화(권한 구별하는거라 아직은 크게 신경 안써도 될듯합니다)
public class WebSecurityConfig {



}