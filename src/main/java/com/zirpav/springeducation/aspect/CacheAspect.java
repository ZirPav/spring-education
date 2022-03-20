package com.zirpav.springeducation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@Aspect
public class CacheAspect {

    private final Map<String, Object> cacheData = new ConcurrentHashMap<>();

    @Around("@annotation(com.zirpav.springeducation.annotation.CacheResult)")
    public Object cacheResultAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String key = keyGenerator(name, args);
        Object result = cacheData.get(key);
        if (result != null) {
            log.info("Получили результат метода: {} из кэша по ключу: {}", name, key);
            return result;
        }
        Object proceed = proceedingJoinPoint.proceed();
        log.info("Сохраняем результат метода: {} в кэше по ключу: {}", name, key);
        cacheData.put(key, proceed);
        return proceed;
    }

    public String keyGenerator(String name, Object[] args) {
        StringBuilder keyFromArgs = new StringBuilder();
        for (Object arg : args) {
            keyFromArgs.append(arg);
            keyFromArgs.append("_");
        }
        return name + "_" + keyFromArgs;
    }
}
