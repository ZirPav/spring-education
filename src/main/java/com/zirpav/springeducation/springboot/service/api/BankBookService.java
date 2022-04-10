package com.zirpav.springeducation.springboot.service.api;

import com.zirpav.springeducation.springboot.model.dto.BankBookDto;

public interface BankBookService {

    BankBookDto getBankBookById(Long bankBookId);
    BankBookDto create(BankBookDto dto);
    BankBookDto update(BankBookDto dto);
    void deleteBankBookById(Long bankBookId);

}
