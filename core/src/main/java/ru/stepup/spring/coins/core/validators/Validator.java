package ru.stepup.spring.coins.core.validators;

public interface Validator<T> {
    void validate(T t);
}