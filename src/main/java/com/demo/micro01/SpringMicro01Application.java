package com.demo.micro01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.micro01.mapper")
public class SpringMicro01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMicro01Application.class, args);
    }
}
