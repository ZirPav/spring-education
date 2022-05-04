package com.zirpav.springeducation.springboot.api;

import com.zirpav.springeducation.springboot.dto.BankBookDto;

import java.util.List;

public interface BankBookService {

    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto findById(Integer bankBookId);
    BankBookDto create(BankBookDto dto);
    BankBookDto update(BankBookDto dto);
    void delete(Integer bankBookId);
    void deleteByUserId(Integer userId);

}
