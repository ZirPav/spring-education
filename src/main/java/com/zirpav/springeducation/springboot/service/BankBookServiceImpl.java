package com.zirpav.springeducation.springboot.service;

import com.zirpav.springeducation.springboot.api.BankBookService;
import com.zirpav.springeducation.springboot.dto.BankBookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, List<BankBookDto>> bankBooks = new ConcurrentHashMap<>();
    private final AtomicInteger userId = new AtomicInteger(1);
    private final AtomicInteger bankBookId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        int userId = this.userId.getAndIncrement();
        int bankBookId = this.bankBookId.getAndIncrement();
        bankBooks.put(userId, new ArrayList<>(Arrays.asList(BankBookDto.builder().number("3").amount(BigDecimal.ONE).currency("12").id(bankBookId).build())));
    }


    @Override
    public List<BankBookDto> getBankBookByUserId(Integer userId) {
        return bankBooks.get(userId);
    }

    @Override
    public BankBookDto getBankBookByBankBookId(Integer bankBookId) {
        for (Map.Entry<Integer, List<BankBookDto>> bankBook : bankBooks.entrySet()) {
            List<BankBookDto> value = bankBook.getValue();
            for (BankBookDto bankBookDto : value) {
                if (bankBookId.equals(bankBookDto.id)) {
                    return bankBookDto;
                }
            }
        }
        throw new NullPointerException(String.format("Отсутствует BankBook по id=%d", bankBookId));
    }

    @Override
    public BankBookDto create(BankBookDto dto, Integer userId) {
        int bankBookId = this.bankBookId.getAndIncrement();
        List<BankBookDto> bankBookDtos = this.bankBooks.get(userId);
        if (bankBookDtos != null) {
            for (BankBookDto bankBookDto : bankBookDtos) {
                if (bankBookDto.number().equals(dto.number()) && bankBookDto.currency().equals(dto.currency())) {
                    throw new RuntimeException("Счет с данной валютой уже имеется в хранилище");
                } else {
                    BankBookDto newBankBookDto = dto.id(bankBookId);
                    bankBookDtos.add(newBankBookDto);
                    bankBooks.put(userId, bankBookDtos);
                    return newBankBookDto;
                }
            }
        }
        BankBookDto newBankBookDto = dto.id(bankBookId);
        List<BankBookDto> newBankBookDtos = List.of(newBankBookDto);
        bankBooks.put(userId, newBankBookDtos);
        return newBankBookDto;
    }

    @Override
    public BankBookDto update(BankBookDto dto, Integer userId) {
        List<BankBookDto> bankBookDtos = this.bankBooks.get(userId);
        if (bankBookDtos != null) {
            for (BankBookDto bankBookDto : bankBookDtos) {
                if (bankBookDto.id().equals(dto.id())) {
                    bankBookDto.amount(dto.amount());
                    bankBookDto.currency(dto.currency());
                    if (!bankBookDto.number().equals(dto.number())) {
                        throw new RuntimeException("Некорректная операция");
                    }
                    return bankBookDto;
                }
            }
        }
        return null;
    }

    @Override
    public BankBookDto deleteBankBookByBankBookId(Integer bankBookId) {
        for (Map.Entry<Integer, List<BankBookDto>> bankBooks : bankBooks.entrySet()) {
            List<BankBookDto> value = bankBooks.getValue();
            BankBookDto bankBookDto = value.get(bankBookId);
            if (bankBookDto != null) {
                value.remove(bankBookId);
                return bankBookDto;
            }
        }
        return null;
    }

    @Override
    public List<BankBookDto> deleteBankBookByUserId(Integer userId) {
        return bankBooks.get(userId);
    }
}