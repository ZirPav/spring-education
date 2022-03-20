package com.zirpav.springeducation;

import com.zirpav.springeducation.api.ExternalService;
import com.zirpav.springeducation.model.Flow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

//todo Поправить кэш с помощью аннотации, поправить beanFactoryPostProcessor
@SpringBootApplication
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class SpringEducationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringEducationApplication.class, args);
        ExternalService externalServiceImpl = (ExternalService) applicationContext.getBean("externalServiceImpl");
        externalServiceImpl.getSlogan();
        externalServiceImpl.getSlogan();

        Flow flow = applicationContext.getBean(Flow.class);
        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);
        flow.run(45);
    }

}
