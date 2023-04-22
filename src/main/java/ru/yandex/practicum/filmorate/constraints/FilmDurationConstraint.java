package ru.yandex.practicum.filmorate.constraints;

import ru.yandex.practicum.filmorate.validators.FilmDurationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilmDurationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmDurationConstraint {
    String message() default "продолжительность фильма должна быть положительной";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
