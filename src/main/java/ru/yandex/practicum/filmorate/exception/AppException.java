package ru.yandex.practicum.filmorate.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import ru.yandex.practicum.filmorate.model.ErrorMessage;


@RequiredArgsConstructor
@Getter
public class AppException extends RuntimeException {
    protected final String message;
    protected final String details;
    protected HttpStatus httpErrorStatus;

    public ErrorMessage prepareErrorMessage() {
        return new ErrorMessage(message, details);
    }
}
