package com.moglix.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.moglix.annotation.OptionalNumber;

public class OptionalNumberValidator implements ConstraintValidator<OptionalNumber, String> {
	 
    @Override
    public void initialize(OptionalNumber paramA) {
    	
    }
 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if(StringUtils.isBlank(value)){
            return true;
        }
        //validate phone numbers of format "1234567890"
        if (Integer.valueOf(value) instanceof Integer) return true;
        else return false;
    }
 
}
