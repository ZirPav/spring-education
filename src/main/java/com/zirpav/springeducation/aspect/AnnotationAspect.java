package com.zirpav.springeducation.aspect;

import com.zirpav.springeducation.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@Aspect
public class AnnotationAspect {

    @Value("${id-not-process}")
    private Integer idNotProcess;

    @Around("@annotation(com.zirpav.springeducation.annotation.CheckRequest)")
    public Object annotateCheckRequestAspect(ProceedingJoinPoint joinPoint) {
        log.info("Вызвали метод: {} аннотированный @CheckRequest", joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object it : args) {
                if (it instanceof ExternalInfo && Objects.equals(((ExternalInfo) it).id(), idNotProcess)) {
                    log.info("Пропускаем выполнение основного метода");
                    return Boolean.FALSE;
                }
                try {
                    log.info("Передаем управление основному методу: {}", joinPoint.getSignature().getName());
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("Пропускаем выполнение основного метода");
        return Boolean.FALSE;
    }
}
