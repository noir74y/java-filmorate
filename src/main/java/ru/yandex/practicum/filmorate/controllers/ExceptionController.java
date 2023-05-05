package ru.yandex.practicum.filmorate.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.AppException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.OtherException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.ErrorMessage;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionController(Exception exception) {
        AppException appException;

        if (exception instanceof NotFoundException)
            appException = (NotFoundException) exception;
        else if (exception instanceof MethodArgumentNotValidException)
            appException = new ValidationException(exception);
        else
            appException = new OtherException(exception);

        return ResponseEntity
                .status(appException.getHttpErrorStatus())
                .body(appException.prepareErrorMessage());
    }
}
