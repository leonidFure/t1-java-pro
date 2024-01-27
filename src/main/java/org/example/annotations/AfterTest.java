package org.example.annotations;

import java.lang.annotation.*;

/**
 * Используется для метки статического метода
 * который будет вызываться каждого метода
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AfterTest {
}
