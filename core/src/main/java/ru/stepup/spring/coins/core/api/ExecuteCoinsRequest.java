package ru.stepup.spring.coins.core.api;

public record ExecuteCoinsRequest(
        String receiverNumber,
        String productId,
        String productType
) {
}
