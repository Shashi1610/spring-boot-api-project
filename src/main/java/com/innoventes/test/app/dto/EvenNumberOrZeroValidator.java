package com.innoventes.test.app.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EvenNumberOrZeroValidator  implements ConstraintValidator<EvenNumberOrZero, Number> {

    @Override
    public void initialize(EvenNumberOrZero constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number==null){
            return true;
        }
        return number.longValue()==0 || number.longValue()%2==0;
    }
}
