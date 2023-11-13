package dev.steadypim.thewhitehw.homework1.exception;

/**
 * Исключение - запись не найдена
 */
public class UtilityRecordNotFoundException extends RuntimeException{
    public UtilityRecordNotFoundException(String message) {
        super(message);
    }
}
