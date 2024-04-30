package com.moglix.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.moglix.annotation.OptionalNull;

public class OptionalNullValidator implements ConstraintValidator<OptionalNull, Object> {

    @Override
    public void initialize(OptionalNull annotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value != null;
    }
}
