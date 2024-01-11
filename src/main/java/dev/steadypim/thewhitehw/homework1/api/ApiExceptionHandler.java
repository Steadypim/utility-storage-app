package dev.steadypim.thewhitehw.homework1.api;

import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * При появлении исключения {@link EntityNotFoundException} возвращает статус 404 и сообщение об ошибке.
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorDTO processNotFoundException(EntityNotFoundException exception) {
        return ErrorDTO.of(List.of(exception.getMessage()));
    }

    /**
     * При появлении исключения {@link MethodArgumentNotValidException} возвращает статус 400 и сообщение об ошибке.
     */
    @Override
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorMessages = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                                                               errorMessages.add(error.getDefaultMessage())
                                                      );

        return ResponseEntity.badRequest().body(ErrorDTO.of(errorMessages));
    }
}
