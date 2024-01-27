package org.example.annotations;

import java.lang.annotation.*;

/**
 * Используется для метки статического метода
 * который будет вызываться перед всеми методами
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeSuite {
}
