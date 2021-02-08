package ru.sbrf.lesson.processing.exceptions;

public class DuplicateRequestException extends BusinessException {
    public DuplicateRequestException(String message) {
        super(message);
    }
}
