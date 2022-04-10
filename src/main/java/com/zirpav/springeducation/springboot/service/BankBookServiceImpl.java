package com.zirpav.springeducation.springboot.service;

import com.zirpav.springeducation.springboot.service.api.BankBookService;
import com.zirpav.springeducation.springboot.mappers.api.BankBookMapper;
import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.dto.CurrencyDto;
import com.zirpav.springeducation.springboot.model.entity.BankBook;
import com.zirpav.springeducation.springboot.model.entity.Currency;
import com.zirpav.springeducation.springboot.repository.BankBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankBookServiceImpl implements BankBookService {

    private final BankBookRepository bankBookRepository;
    private final BankBookMapper bankBookMapper;

    public BankBookServiceImpl(BankBookRepository bankBookRepository, BankBookMapper bankBookMapper) {
        this.bankBookRepository = bankBookRepository;
        this.bankBookMapper = bankBookMapper;
    }

    @Override
    public BankBookDto getBankBookById(Long bankBookId) {
        return bankBookMapper.convertToBankBookDto(bankBookRepository.getById(bankBookId));
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        BankBook bankBook = bankBookMapper.convertToBankBookEntity(bankBookDto);
        return bankBookMapper.convertToBankBookDto(bankBookRepository.save(bankBook));
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBook bankBook = bankBookRepository.findById(bankBookDto.id()).orElseThrow(() -> new RuntimeException("User not found!"));
        bankBook.amount(bankBookDto.amount());
        CurrencyDto currencyDto = bankBookDto.currency();
        Currency currency = bankBook.currency();
        if (currency != null && currencyDto != null) {
            currency.name(currencyDto.name());
        }
        bankBook.currency(currency);
        return bankBookMapper.convertToBankBookDto(bankBookRepository.save(bankBook));
    }

    @Override
    public void deleteBankBookById(Long bankBookId) {
        bankBookRepository.deleteById(bankBookId);
    }

}
