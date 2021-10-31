package com.example.aop;

import com.example.aop.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://wangmaoxiong.blog.csdn.net/article/details/108164388

@SpringBootApplication
public class AopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Autowired
    CalculatorService service;

    @Override
    public void run(String... args) throws Exception {
        service.add(2,3);

        //引发异常的方法
        //service.div(1,0);
    }
}
