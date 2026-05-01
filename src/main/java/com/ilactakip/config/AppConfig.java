package com.ilactakip.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

    @Value("8070")
    private String port;

    @Bean
    @Lazy
    @Primary
    public String appInfo() {
        return "İlaç Takip Sistemi port: " + port;
    }

    @PostConstruct
    public void init() {
        System.out.println("AppConfig başlatıldı.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AppConfig kapatılıyor.");
    }
}
