package ru.sbrf.lesson.processing.exceptions;

public class AccountIdNotFoundException extends BusinessException {

    public AccountIdNotFoundException(String message) {
        super(message);
    }
}
