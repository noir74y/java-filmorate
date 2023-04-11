package ru.yandex.practicum.filmorate.constraints;

import ru.yandex.practicum.filmorate.validators.FilmReleaseDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint (validatedBy = FilmReleaseDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmReleaseDateConstraint {
    String message() default "film has wrong release date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
