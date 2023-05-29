package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Generic;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericControllerTest {
    protected static Validator validator;
    protected Set<ConstraintViolation<Generic>> violations;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected boolean isConstraintTriggered(Set<ConstraintViolation<Generic>> violations, String messageToSearch) {
        for (ConstraintViolation<Generic> constraintViolation : violations)
            if (constraintViolation.getMessageTemplate().equals(messageToSearch))
                return true;
        return false;
    }
}
