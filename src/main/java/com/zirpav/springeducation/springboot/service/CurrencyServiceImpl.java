package com.zirpav.springeducation.springboot.service;

import com.zirpav.springeducation.springboot.service.api.CurrencyService;
import com.zirpav.springeducation.springboot.model.dto.CurrencyDto;
import com.zirpav.springeducation.springboot.model.entity.Currency;
import com.zirpav.springeducation.springboot.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @PostConstruct
    public void init() {
        Currency rub = Currency.builder().name("RUB").build();
        Currency eur = Currency.builder().name("EUR").build();
        Currency usd = Currency.builder().name("USD").build();
        Currency gbp = Currency.builder().name("GBP").build();
        List<Currency> currencies = List.of(rub, eur, usd, gbp);
        currencyRepository.saveAll(currencies);
    }

    @Override
    public CurrencyDto create(final CurrencyDto currencyDto) {
        final Currency currency = Currency.builder().name(currencyDto.name()).build();
        return mapToDto(currencyRepository.save(currency));
    }

    public CurrencyDto mapToDto(final Currency currency) {
        return CurrencyDto.builder()
                .id(currency.id())
                .name(currency.name())
                .build();
    }
}
