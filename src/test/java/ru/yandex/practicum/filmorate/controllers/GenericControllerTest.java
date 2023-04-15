package ru.yandex.practicum.filmorate.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.GenericModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
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
