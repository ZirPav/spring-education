package com.zirpav.springeducation.config;

import com.zirpav.springeducation.annotation.CacheResult;
import com.zirpav.springeducation.model.CacheData;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
@Component
public class CacheResultBeanPostProcessor implements BeanPostProcessor {

    private final CacheData cacheData;

    public CacheResultBeanPostProcessor(CacheData cacheData) {
        this.cacheData = cacheData;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        for (Method method : aClass.getMethods()) {
            if (method.isAnnotationPresent(CacheResult.class)) {
                method.setAccessible(true);
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                    Object proceed = invocation.proceed();
                    for (Method methodAdvice : invocation.getThis().getClass().getMethods()) {
                        if (invocation.getMethod().getName().equals(methodAdvice.getName()) &&
                                AnnotationUtils.findAnnotation(methodAdvice, CacheResult.class) != null) {
                            Object[] arguments = invocation.getArguments();
                            StringBuffer key = keyGenerator(aClass, methodAdvice, arguments);
                            Object object = cacheData.getCacheData().get(key.toString());
                            if (object == null) {
                                log.info("Результат выполнения: {}, сохраняем в кэше по ключу: {}", proceed, key);
                                cacheData.getCacheData().put(key.toString(), proceed);
                                log.info("Размер кэша size={}", cacheData.getCacheData().size());
                            }
                        }
                    }
                    return proceed;
                });
                return proxyFactory.getProxy();
            }
        }
        return bean;
    }

    private StringBuffer keyGenerator(Class<?> aClass, Method method, Object[] arguments) {
        StringBuffer key = new StringBuffer();
        key.append(aClass.getSimpleName()).append("_").append(method.getName());
        Parameter[] parameters = method.getParameters();
        if (parameters.length != 0) {
            for (Parameter parameter : parameters) {
                key.append("_");
                key.append(parameter.getName());
            }
        }
        if (arguments.length != 0) {
            for (Object argument : arguments) {
                key.append("_");
                key.append(argument);
            }
        }

        return key;
    }
}
