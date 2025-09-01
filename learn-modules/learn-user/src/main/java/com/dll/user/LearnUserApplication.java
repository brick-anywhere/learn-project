package com.dll.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author dll
 * @date 2025-07-08 17:40
 */
@SpringBootApplication
@MapperScan(basePackages = "com.dll.user.mapper")
@EnableDiscoveryClient
public class LearnUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnUserApplication.class, args);
    }
}
