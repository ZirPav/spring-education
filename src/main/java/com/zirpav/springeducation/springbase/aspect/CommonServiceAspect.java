package com.zirpav.springeducation.springbase.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class CommonServiceAspect {

    @Before("within(com.zirpav.springeducation.service.*)")
    public void beforeAllMethodsInServicePackage(JoinPoint joinPoint) {
        log.info("beforeMethodInServicePackage: Before method {}", joinPoint.getSignature().getName());
    }

    @After("within(com.zirpav.springeducation.service.*)")
    public void afterAllMethodsInServicePackage(JoinPoint joinPoint) {
        log.info("afterMethodInServicePackage: After method {}", joinPoint.getSignature().getName());
    }

}
