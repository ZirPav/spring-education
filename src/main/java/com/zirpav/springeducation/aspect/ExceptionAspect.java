package com.zirpav.springeducation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ExceptionAspect {

    @AfterThrowing(value = "execution(* com.zirpav..*(..))", throwing = "exception")
    public void afterAllMethodThrowAdvice(JoinPoint.StaticPart staticPart, Exception exception) {
        log.error("afterAllMethodThrowAdvice: After throw {} with exception {}", staticPart.getSignature(), exception.getMessage());
    }
}
