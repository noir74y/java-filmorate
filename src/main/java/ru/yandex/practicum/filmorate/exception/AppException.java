package ru.yandex.practicum.filmorate.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.yandex.practicum.filmorate.model.ErrorMessage;

public class AppException extends RuntimeException {
    @Getter
    protected HttpStatus httpErrorStatus;
    protected String cause;
    protected String message;

    public ErrorMessage prepareErrorMessage() {
        return new ErrorMessage(cause, message);
    }

}
