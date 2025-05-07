package com.app.my_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// การตั้งค่า CORS แบบนี้ (อนุญาตทุก Origins, Methods, และ Headers) มักจะใช้ใน ระหว่างการพัฒนา
// หรือในกรณีที่ Application ของคุณต้องการให้ Frontend ที่รันอยู่บน Domain ใดๆ
// ก็ตามสามารถเข้าถึง API ได้โดยไม่มีข้อจำกัดด้าน CORS
@Configuration
public class WebConfig { // CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
