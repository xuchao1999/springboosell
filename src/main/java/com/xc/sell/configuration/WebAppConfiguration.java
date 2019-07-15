package com.xc.sell.configuration;

import com.xc.sell.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor()).addPathPatterns("/**");
    }
}
