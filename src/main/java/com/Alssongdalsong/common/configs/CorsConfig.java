package com.Alssongdalsong.common.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:8091", "http://127.0.0.1:5500") // 허용 url명시 > 우리 서버로 들어올 수 있는 서비스를 명시해주는 것
//                추후 front는 8091로 잡아서 진행할 예정.

                .allowedMethods("*") // 모든 Method 허용여부
                .allowedHeaders("*") // 모든 헤더 허락여부
                .allowCredentials(true); // 보안처리 여부
    }
}
