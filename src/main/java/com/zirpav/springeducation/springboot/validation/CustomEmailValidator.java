package com.zirpav.springeducation.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<CustomEmail, String> {

    private static final String MANDATORY_SYMBOLS_ONE = "@";
    private static final String MANDATORY_SYMBOLS_TWO = ".";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains(MANDATORY_SYMBOLS_ONE) && value.contains(MANDATORY_SYMBOLS_TWO);
    }
}
