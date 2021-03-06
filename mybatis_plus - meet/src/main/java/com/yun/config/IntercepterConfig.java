package com.yun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
//    private TokenInterceptor tokenInterceptor;
//
//    public IntercepterConfig(TokenInterceptor tokenInterceptor) {
//        this.tokenInterceptor = tokenInterceptor;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> excludePath = new ArrayList<>();
//        excludePath.add("/login");
//        excludePath.add("/static/**");
//        excludePath.add("/assets/**");
//        excludePath.add("/manager");
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludePath);
//    }

    //配置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
