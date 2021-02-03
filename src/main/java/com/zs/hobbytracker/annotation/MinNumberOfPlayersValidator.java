package com.zs.hobbytracker.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator class for Annotation MinNumberOfPlayers
 */
public class MinNumberOfPlayersValidator implements ConstraintValidator<MinNumberOfPlayers, Integer> {

    /**
     * To initialize data
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MinNumberOfPlayers constraintAnnotation) {
    }

    /**
     * @param value   accepts value
     * @param context accepts ConstraintValidatorContext context
     * @return whether value is valid or not
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        if (value < 2)
            return false;
        return true;
    }
}

