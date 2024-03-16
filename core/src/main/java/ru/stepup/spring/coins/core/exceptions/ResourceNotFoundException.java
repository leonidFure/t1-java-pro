package ru.stepup.spring.coins.core.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public ResourceNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
