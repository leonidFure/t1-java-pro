package ru.stepup.spring.coins.core.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorDto {
    private String code;
    private String message;
    private LocalDateTime date;

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
