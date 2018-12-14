package com.mingo.oacheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OaCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaCheckApplication.class, args);
    }
}
