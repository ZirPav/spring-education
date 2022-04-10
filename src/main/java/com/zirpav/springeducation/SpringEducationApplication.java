package com.zirpav.springeducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@PropertySource("classpath:application.yaml")
// docker run --name account-postgres -p 5432:5432 -e POSTGRES_USER=account -e POSTGRES_PASSWORD=account -e POSTGRES_DB=account -d postgres:14
public class SpringEducationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEducationApplication.class, args);
    }

}
