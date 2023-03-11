package com.ya.bosssave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ya.bosssave.mapper")
public class BosssaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(BosssaveApplication.class, args);
    }

}
