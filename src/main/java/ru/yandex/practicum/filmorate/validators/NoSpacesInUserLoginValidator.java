package ru.yandex.practicum.filmorate.validators;

import ru.yandex.practicum.filmorate.constraints.NoSpacesInUserLoginConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpacesInUserLoginValidator  implements
        ConstraintValidator<NoSpacesInUserLoginConstraint, String> {
    @Override
    public void initialize(NoSpacesInUserLoginConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return login.matches("\\S+");
    }
}
