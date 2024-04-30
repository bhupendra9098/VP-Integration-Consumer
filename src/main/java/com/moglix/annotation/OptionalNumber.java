package com.moglix.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.moglix.validator.OptionalNumberValidator;

@Documented
@Constraint(validatedBy = OptionalNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalNumber {
  
      
    String message() default "invalid format";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
       
}
