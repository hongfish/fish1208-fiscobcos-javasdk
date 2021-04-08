package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FiscoBcosJavasdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiscoBcosJavasdkApplication.class, args);
    }

}
