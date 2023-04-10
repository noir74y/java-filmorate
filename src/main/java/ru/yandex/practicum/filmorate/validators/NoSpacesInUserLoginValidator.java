package ru.yandex.practicum.filmorate.validators;

import ru.yandex.practicum.filmorate.constraints.NoSpacesInUserLoginConstraint;

import javax.validation.ConstraintValidator;

public class NoSpacesInUserLoginValidator  implements
        ConstraintValidator<NoSpacesInUserLoginConstraint, String> {
}
