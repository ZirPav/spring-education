package com.zirpav.springeducation.springboot.mappers.api;

import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.entity.BankBook;

public interface BankBookMapper {

    BankBookDto convertToBankBookDto(BankBook bankBook);
    BankBook convertToBankBookEntity(BankBookDto bankBookDto);

}
