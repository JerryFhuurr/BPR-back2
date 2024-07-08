package com.bpr.bprbackend2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication  // use this when need to connect database
@SpringBootApplication
@MapperScan("com.bpr.bprbackend2.mapper")
public class BprBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(BprBackend2Application.class, args);
    }

}
