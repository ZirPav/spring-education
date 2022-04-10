package com.zirpav.springeducation.springboot.mappers.impl;

import com.zirpav.springeducation.springboot.mappers.api.BankBookMapper;
import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.dto.CurrencyDto;
import com.zirpav.springeducation.springboot.model.dto.UserDto;
import com.zirpav.springeducation.springboot.model.entity.BankBook;
import com.zirpav.springeducation.springboot.model.entity.Currency;
import com.zirpav.springeducation.springboot.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class BankBookMapperImpl implements BankBookMapper {

    @Override
    public BankBookDto convertToBankBookDto(BankBook bankBook) {
        return BankBookDto.builder()
                .id(bankBook.id())
                .user(UserDto.builder()
                        .id(bankBook.user().id())
                        .build())
                .number(bankBook.number())
                .amount(bankBook.amount())
                .currency(CurrencyDto.builder()
                        .id(bankBook.currency().id())
                        .build())
                .build();
    }

    @Override
    public BankBook convertToBankBookEntity(BankBookDto bankBookDto) {
        return BankBook.builder()
                .id(bankBookDto.id())
                .user(User.builder()
                        .id(bankBookDto.user().id())
                        .build())
                .number(bankBookDto.number())
                .amount(bankBookDto.amount())
                .currency(Currency.builder()
                        .id(bankBookDto.currency().id())
                        .build())
                .build();
    }

}
