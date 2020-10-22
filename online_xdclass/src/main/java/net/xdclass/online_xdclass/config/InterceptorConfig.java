package net.xdclass.online_xdclass.config;

import net.xdclass.online_xdclass.interceptor.CorsInterceptor;
import net.xdclass.online_xdclass.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(corsInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor())
                //拦截的路径
                .addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截的路径
                .excludePathPatterns("/api/v1/pri/user/login", "/api/v1/pri/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
