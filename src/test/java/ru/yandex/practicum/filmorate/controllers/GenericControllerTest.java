package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.GenericModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
public class GenericControllerTest {
    protected static Validator validator;
    protected Set<ConstraintViolation<GenericModel>> violations;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected boolean isConstraintTriggered(Set<ConstraintViolation<GenericModel>> violations, String messageToSearch) {
        for (ConstraintViolation<GenericModel> constraintViolation : violations)
            if (constraintViolation.getMessage().equals(messageToSearch))
                return true;
        return false;
    }
}
