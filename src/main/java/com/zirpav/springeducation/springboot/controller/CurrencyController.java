package com.zirpav.springeducation.springboot.controller;

import com.zirpav.springeducation.springboot.service.api.CurrencyService;
import com.zirpav.springeducation.springboot.model.dto.CurrencyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("currency/create")
    public ResponseEntity<CurrencyDto> createCurrency(@RequestBody CurrencyDto currencyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.create(currencyDto));
    }
}
