package com.zirpav.springeducation.springboot.service;

import com.zirpav.springeducation.springboot.api.BankBookService;
import com.zirpav.springeducation.springboot.dto.BankBookDto;
import com.zirpav.springeducation.springboot.exception.BankBookNotFoundException;
import com.zirpav.springeducation.springboot.exception.BankBookNumberCannotBeModifiedException;
import com.zirpav.springeducation.springboot.exception.BankBookWithCurrencyAlreadyHaveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBooks = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        bankBooks.put(1, BankBookDto.builder()
                .id(1)
                .userId(1)
                .number("number1")
                .amount(BigDecimal.ONE)
                .currency("RUB")
                .build());
    }

    @Override
    public BankBookDto findById(Integer bankBookId) {
        BankBookDto bankBookDto = bankBooks.get(bankBookId);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Счёт не найден!");
        }
        return bankBookDto;
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBooks.values().stream()
                .filter(bankBookDto -> userId.equals(bankBookDto.userId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto create(BankBookDto dto) {
        boolean hasBankBook = bankBooks.values().stream()
                .anyMatch(bankBook -> bankBook.userId.equals(dto.userId)
                        && bankBook.number().equals(dto.number())
                        && bankBook.currency().equals(dto.currency()));
        if (hasBankBook) {
            throw new BankBookWithCurrencyAlreadyHaveException("Счет с данной валютой уже имеется!");
        }

        int id = sequenceId.getAndIncrement();
        dto.id(id);
        bankBooks.put(id, dto);
        return dto;
    }

    @Override
    public BankBookDto update(BankBookDto dto) {
        BankBookDto bankBookDto = bankBooks.get(dto.id());
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Лицевой счет не найден!");
        }
        if (!bankBookDto.number().equals(dto.number())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }
        bankBooks.put(dto.id(), dto);
        return dto;
    }

    @Override
    public void delete(Integer bankBookId) {
        bankBooks.remove(bankBookId);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        bankBooks.values().removeIf(it -> it.userId().equals(userId));
    }

}
