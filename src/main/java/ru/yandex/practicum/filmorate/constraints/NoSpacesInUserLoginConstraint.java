package ru.yandex.practicum.filmorate.constraints;

import ru.yandex.practicum.filmorate.validators.NoSpacesInUserLoginValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoSpacesInUserLoginValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface  NoSpacesInUserLoginConstraint {
}
