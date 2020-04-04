package com.zyj.wechartexam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zyj.wechartexam")
@MapperScan(value = "com.zyj.wechartexam.webCtr.mapper")
public class WechartexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechartexamApplication.class, args);
    }

}
