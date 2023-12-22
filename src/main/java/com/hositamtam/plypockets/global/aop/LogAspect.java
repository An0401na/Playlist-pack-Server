package com.hositamtam.plypockets.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //이 클래스가 Aspect클래스임을 명시
@Slf4j
@Component //스프링 빈으로 등록
public class LogAspect {

    @Pointcut("execution(* com.hositamtam.plypockets..*(..))")
    public void all() {
    }
    @Pointcut("execution(* com.hositamtam.plypockets..*Controller.*(..))")
    public void controller() {
    }
    @Pointcut("execution(* com.hositamtam.plypockets..*Service.*(..))")
    public void service(){}
    @Around("all()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("[TIME] {}.{} : {}Ms,",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    method.getName(),
                    timeMs);
        }
    }
    //특정 조인포인트에서 수행될 부가기능을 정리
    @Before("controller() || service()")
    public void beforeLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("[START METHOD] : {}.{}", joinPoint.getTarget().getClass().getSimpleName(),method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if(arg != null) {
                log.info("type = {}", arg.getClass().getSimpleName());
                log.info("value = {}", arg);
            }

        }

    }
    @After("controller() || service()")
    public void afterLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("[END METHOD] : {}.{}", joinPoint.getTarget().getClass().getSimpleName(),method.getName());


    }



}