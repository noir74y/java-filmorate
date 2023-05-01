package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {
    public NotFoundException(String message, String details) {
        super(message, details);
        httpErrorStatus = HttpStatus.NOT_FOUND;
    }
}
