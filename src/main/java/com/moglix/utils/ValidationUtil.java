package com.moglix.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.springframework.stereotype.Component;

import com.moglix.annotation.OptionalBlank;
import com.moglix.annotation.OptionalNull;
import com.moglix.exception.MoglixUserError;
import com.moglix.exception.MoglixUserException;

@Component
public class ValidationUtil<T> {

	public boolean validateRequest(T input, StringBuilder error, boolean validateOptionalBlanks) throws MoglixUserException {
		boolean result = true;
        Set<ConstraintViolation<T>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(input);
        if (violations.size() > 0) {
            for (ConstraintViolation<T> violation : violations) {
                if (validateOptionalBlanks || !violation.getConstraintDescriptor().getAnnotation().annotationType().equals(OptionalBlank.class)
                        && !violation.getConstraintDescriptor().getAnnotation().annotationType().equals(OptionalNull.class)) {
                	error.append(violation.getPropertyPath()).append(' ').append(violation.getMessage()).append('|');
                }
            }
            error = error.deleteCharAt(error.length() - 1);
            result = false;
        } 
        if(!result) {
    		throw new MoglixUserException(MoglixUserError.MISSING_REQUIRED_PARAMS, error.toString());
        }
        return result;
	}
}
