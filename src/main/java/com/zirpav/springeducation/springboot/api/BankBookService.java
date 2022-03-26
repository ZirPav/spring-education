package com.zirpav.springeducation.springboot.api;

import com.zirpav.springeducation.springboot.dto.BankBookDto;

import java.util.List;

public interface BankBookService {

    List<BankBookDto> getBankBookByUserId(Integer userId);
    BankBookDto getBankBookByBankBookId(Integer bankBookId);
    BankBookDto create(BankBookDto dto, Integer userId);
    BankBookDto update(BankBookDto dto, Integer userId);
    BankBookDto deleteBankBookByBankBookId(Integer bankBookId);
    List<BankBookDto> deleteBankBookByUserId(Integer userId);

}
