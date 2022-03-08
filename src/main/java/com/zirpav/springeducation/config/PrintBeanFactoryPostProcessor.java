package com.zirpav.springeducation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String[] beanNamesForAnnotation = beanFactory.getBeanNamesForAnnotation(Component.class);
            if (beanDefinition.isPrototype() && hasBeanWithAnnotation(beanNamesForAnnotation, beanDefinitionName)) {
                log.info("Получаем бин сo scope prototype и c аннотацией Component, имя бина: {}", beanDefinitionName);
            }
        }
    }

    public boolean hasBeanWithAnnotation(String[] beanNamesForAnnotation, String beanDefinitionName) {
        return Arrays.asList(beanNamesForAnnotation).contains(beanDefinitionName);
    }
}
