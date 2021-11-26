package com.daelim.yeondutalk.config;

import com.daelim.yeondutalk.argumentresolver.LogInUserArgumentResolver;
import com.daelim.yeondutalk.interceptor.LogInCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LogInCheckInterceptor logInCheckInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LogInUserArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInCheckInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/yeondu/login" , "/yeondu/join", "/error", "/index.html", "/css/**");
    }
}
