package com.gc26.spring.apirest;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiRestApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8081"));
        app.run(args);
    }
}
