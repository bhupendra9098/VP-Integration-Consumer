package com.moglix.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.moglix.annotation.OptionalBlank;

public class OptionalBlankValidator implements ConstraintValidator<OptionalBlank, String> {

    @Override
    public void initialize(OptionalBlank annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value);
    }
}
