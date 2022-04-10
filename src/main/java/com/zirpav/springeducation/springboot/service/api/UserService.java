package com.zirpav.springeducation.springboot.service.api;

import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Long id);
    List<BankBookDto> getAllBankBookDtos(Long id);

}
