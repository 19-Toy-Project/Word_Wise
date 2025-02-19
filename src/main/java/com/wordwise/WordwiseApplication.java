package com.wordwise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@EnableAspectJAutoProxy
@Slf4j
public class WordwiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordwiseApplication.class, args);
    }

}
