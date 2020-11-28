package com.example.common.aop;

import com.example.common.BaseException;
import com.example.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerAop {

    private static Class<?> authenticationException;

    @Pointcut("execution(public com.example.common.Result *(..))")
    public void controllerMethod() {
    }


    @Around("controllerMethod()")
    public Object handleControllerMethod(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();
        Result<?> result;

        try {
            result = (Result<?>) point.proceed();
            log.info(point.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handleException(point, e);
        }

        return result;
    }

    public Result<?> handleException(ProceedingJoinPoint point, Throwable e) {
        Result<?> result = new Result<>();

        // 已知异常
        if (e instanceof BaseException) {
            result.setMessage(e.getLocalizedMessage());
            result.setCode(Result.FAIL);
        } else if (e instanceof AuthenticationException) {
            result.setMessage(e.getMessage());
            result.setCode(Result.NO_LOGIN);
        } else {
            log.error(point.getSignature() + " error ", e);
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMessage(e.toString());
            result.setCode(Result.FAIL);
        }

        return result;
    }

}
