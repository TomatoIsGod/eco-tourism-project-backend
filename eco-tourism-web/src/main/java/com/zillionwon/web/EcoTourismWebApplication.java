package com.zillionwon.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Spring Boot 启动类
 * @author InwardFlow
 */
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.zillionwon.*.config")
@MapperScan("com.zillionwon.*.mapper")
public class EcoTourismWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcoTourismWebApplication.class, args);
    }

}
