package org.example.annotations;


import java.lang.annotation.*;

/**
 * Используется для метки статического метода
 * который будет вызываться после всех тестами
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AfterSuite {
}
