package com.zs.hobbytracker.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that default number of players should be 2 if not specified
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinNumberOfPlayersValidator.class)
public @interface MinNumberOfPlayers {
    Class<?>[] groups() default {};

    String message() default "the integer must be greater than or equal to 2!";

    Class<? extends Payload>[] payload() default {};

    int val() default 2;
}
