package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationException extends AppException {
    public ValidationException(Exception exception) {
        httpErrorStatus = HttpStatus.BAD_REQUEST;
        exception = (MethodArgumentNotValidException) exception;
    }
}
