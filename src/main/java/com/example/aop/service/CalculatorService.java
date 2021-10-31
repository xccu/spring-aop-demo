package com.example.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculatorService {

    public int add(int i, int j) {
        int result = i + j;
        log.info("add:" + result);
        return result;
    }

    public int sub(int i, int j) {
        int result = i - j;
        log.info("sub:" + result);
        return result;
    }

    public int mul(int i, int j) {
        int result = i * j;
        log.info("mul:" + result);
        return result;
    }

    public int div(int i, int j) {
        int result = i / j;
        log.info("div:" + result);
        return result;
    }
}
