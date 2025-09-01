package com.dll.score;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author dll
 * @date 2025-07-18 09:21
 */
@SpringBootApplication
@MapperScan(basePackages = "com.dll.score.mapper")
@EnableDiscoveryClient
public class LearnScoreApplication {
}
