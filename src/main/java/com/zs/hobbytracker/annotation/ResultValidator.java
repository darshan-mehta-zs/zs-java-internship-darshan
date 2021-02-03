package com.zs.hobbytracker.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator class for result annotation
 */
public class ResultValidator implements ConstraintValidator<Result, String> {

    /**
     * To initialise data
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(Result constraintAnnotation) {

    }

    /**
     * @param value   accepts value
     * @param context ConstraintValidatorContext context
     * @return whether value is valid or not
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value != null)
            return true;
        return false;
    }
}
