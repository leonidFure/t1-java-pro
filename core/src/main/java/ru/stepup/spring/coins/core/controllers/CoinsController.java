package ru.stepup.spring.coins.core.controllers;

import org.springframework.web.bind.annotation.*;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.services.CoinsService;

@RestController
@RequestMapping("/api/v1/coins")
public class CoinsController {
    private final CoinsService coinsService;

    public CoinsController(CoinsService coinsService) {
        this.coinsService = coinsService;
    }

    // /api/v1/coins/937456-34598345-34598345-3596325

    @PostMapping("/execute")
    public ExecuteCoinsResponse execute(@RequestBody ExecuteCoinsRequest request, @RequestHeader("USERID") String userId) {
        return coinsService.execute(request, userId);
    }

    // GET TRANSACTIONS
}
