package com.zirpav.springeducation.springboot.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CustomEmailValidator.class)
public @interface CustomEmail {

    String message() default "Некорректный email!"; //ключ ValidationMessages.properties
    Class<?>[] groups() default { }; //группа проверки
    Class<? extends Payload>[] payload() default { }; //полезная нагрузка

}
