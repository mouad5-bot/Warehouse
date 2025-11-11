package com.progresssoft.warehouse.aop.logging;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodLoggingAspect {

    private static final String COLOR_INFO = "\u001B[35m";
    private static final String COLOR_PARAM = "\u001B[32m";
    private static final String RESET_COLOR = "\u001B[0m";

    @Before("execution(* com.progresssoft.warehouse.service.impl.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        log.info("{}>>> Entering method: {}{}", COLOR_INFO, joinPoint.getSignature().toShortString(), RESET_COLOR);
        for (int idx = 0; idx < arguments.length; idx++) {
            log.info("{}Parameter [{}]: {}{}", COLOR_PARAM, idx + 1, arguments[idx], RESET_COLOR);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.progresssoft.warehouse.service.impl.*.*(..))", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        String method = joinPoint.getSignature().toShortString();
        log.error("\u001B[31m!!! ERROR !!!\u001B[0m \u001B[33mMethod failed:\u001B[0m {}", method);
        log.error("\u001B[31m!!! ERROR !!!\u001B[0m \u001B[33mError details:\u001B[0m {}", exception.getMessage());
    }

    @AfterReturning(pointcut = "execution(* com.progresssoft.warehouse.service.impl.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        log.info("{}<<< Exiting method: {}{}", COLOR_INFO, method, RESET_COLOR);
        log.info("<<< Return value: {}", result);
    }

}