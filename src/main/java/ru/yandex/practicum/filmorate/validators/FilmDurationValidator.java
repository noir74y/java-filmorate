package ru.yandex.practicum.filmorate.validators;

import ru.yandex.practicum.filmorate.constraints.FilmDurationConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

public class FilmDurationValidator implements
        ConstraintValidator<FilmDurationConstraint, Duration> {
    @Override
    public void initialize(FilmDurationConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
        return duration.toSeconds() >= 0;
    }
}
