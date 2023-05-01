package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends AppException {
    public ValidationException(String message, String details) {
        super(message, details);
        httpErrorStatus = HttpStatus.BAD_REQUEST;
    }
}
