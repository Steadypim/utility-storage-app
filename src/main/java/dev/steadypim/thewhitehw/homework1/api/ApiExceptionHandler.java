package dev.steadypim.thewhitehw.homework1.api;

import dev.steadypim.thewhitehw.homework1.exception.UtilityRecordNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * При появлении исключения {@link UtilityRecordNotFoundException} возвращает статус 404 и сообщение об ошибке.
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UtilityRecordNotFoundException.class)
    public @ResponseBody ErrorDTO processNotFoundException(UtilityRecordNotFoundException exception) {
        return ErrorDTO.of(exception.getMessage());
    }
}
