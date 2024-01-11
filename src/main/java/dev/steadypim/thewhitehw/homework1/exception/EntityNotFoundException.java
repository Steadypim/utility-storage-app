package dev.steadypim.thewhitehw.homework1.exception;

/**
 * Исключение - сущность не найдена
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
