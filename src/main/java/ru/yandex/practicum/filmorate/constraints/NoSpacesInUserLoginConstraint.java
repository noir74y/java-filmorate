package ru.yandex.practicum.filmorate.constraints;

import ru.yandex.practicum.filmorate.validators.NoSpacesInUserLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint (validatedBy = NoSpacesInUserLoginValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpacesInUserLoginConstraint {
    String message() default "login can not contain whitespaces";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
