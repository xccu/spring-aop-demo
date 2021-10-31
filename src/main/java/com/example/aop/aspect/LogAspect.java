package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 前置通知：目标方法执行以前执行如下方法体的内容
     * @param point
     */
    @Before("execution(public int com.example.aop.service.*.*(int, int))")
    public void before(JoinPoint point){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        log.info("前置通知：" + methodName + ",参数：" + args);
    }

    /**
     * 后置通知：目标方法执行以后执行如下方法体的内容，无论是否发生异常。
     * @param point
     */
    @After(("execution(public int com.example.aop.service.*.*(int, int))"))
    public void after(JoinPoint point){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        log.info("后置通知：" + methodName + ",参数：" + args);
    }

    /**
     * 返回通知：目标方法正常执行完毕时执行如下代码
     * 通过returning属性指定连接点方法返回的结果放置在result变量中，
     * 在返回通知方法中可以从result变量中获取连接点方法的返回结果了。
     *
     * @param point
     * @param result
     */
    @AfterReturning(
            value="execution(public int com.example.aop.service.*.*(int, int))",
            returning="result")
    public void afterReturning(JoinPoint point, Object result){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        log.info("返回通知：" + methodName + ",参数：" + args + ",返回：" + result);
    }

    /**
     * 异常通知：目标方法发生异常的时候执行如下代码
     * 通过throwing属性指定连接点方法出现异常信息存储在ex变量中，
     * 在异常通知方法中就可以从ex变量中获取异常信息了
     *
     * @param point
     * @param ex
     */
    @AfterThrowing(value="execution(public int com.example.aop.service.*.*(int, int))",
            throwing="ex")
    public void afterThrowing(JoinPoint point, Exception ex){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        log.info("异常通知：" + methodName + ",参数：" + args + ",异常：" + ex);
    }

    /**
     * 环绕通知：目标方法执行先后分别执行一些代码，发生异常的时候执行另一些代码
     * @param pdj
     * @return
     */
    @Around("execution(public int com.example.aop.service.*.*(int, int))")
    public Object around(ProceedingJoinPoint pdj){
        //result为连接点的放回结果
        Object result = null;
        String methodName = pdj.getSignature().getName();

        //前置通知方法
        log.info("环绕——前置通知：" + methodName + ",参数：" + Arrays.asList(pdj.getArgs()));

        //执行目标方法
        try {
            result = pdj.proceed();
            //返回通知方法
            log.info("环绕——返回通知：" + methodName + ",返回：" + result);
        } catch (Throwable e) {
            //异常通知方法
            log.error("环绕——异常：" + methodName + ",异常：" + e);
        }

        //后置通知
        log.info("环绕——后置通知：" + methodName);
        return result;
    }

}
