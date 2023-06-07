package com.example.academia.service.autenticacao;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/",
                        "/logar",
                        "/registre",
                        "/esqueceu",
                        "/novoUsuario",
                        "/error",
                        "/vendor/**",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/favicon.ico"
                );
    }
}

