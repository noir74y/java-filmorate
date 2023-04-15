package ru.yandex.practicum.filmorate.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

class UserControllerTest extends GenericControllerTest {
    private User user;

    @Test
    void checkValidUser() throws Exception {
        user = User.builder()
                .login("dolore")
                .email("mail@mail.ru")
                .birthday(LocalDate.now())
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    void checkInValidUser() throws Exception {
        user = User.builder()
                .login("dol ore")
                .email("mailmail.ru")
                .birthday(LocalDate.now().plus(1, ChronoUnit.DAYS))
                .build();

        violations = validator.validate(user);

        assertEquals(3, violations.size());
        assertTrue(isConstraintTriggered(violations, "логин не может содержать пробелы"));
        assertTrue(isConstraintTriggered(violations, "{javax.validation.constraints.Email.message}"));
        assertTrue(isConstraintTriggered(violations, "дата рождения не может быть в будущем"));
    }
}