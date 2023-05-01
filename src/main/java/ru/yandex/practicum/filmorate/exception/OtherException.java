package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;

public class OtherException extends AppException {
    public OtherException(String message, String details) {
        super(message, details);
        httpErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
