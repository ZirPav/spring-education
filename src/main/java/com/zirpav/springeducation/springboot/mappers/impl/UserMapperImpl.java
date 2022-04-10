package com.zirpav.springeducation.springboot.mappers.impl;

import com.zirpav.springeducation.springboot.mappers.api.UserMapper;
import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.dto.UserDto;
import com.zirpav.springeducation.springboot.model.entity.BankBook;
import com.zirpav.springeducation.springboot.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto convertToUserDto(User user) {
        return UserDto.builder()
                .id(user.id())
                .name(user.name())
                .email(user.email())
                .bankBooks(Optional.ofNullable(user.bankBooks()).orElse(Collections.emptyList()).stream()
                        .map(it -> BankBookDto.builder()
                                .id(it.id())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public User convertToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.id())
                .name(userDto.name())
                .email(userDto.email())
                .bankBooks(Optional.ofNullable(userDto.bankBooks()).orElse(Collections.emptyList()).stream()
                        .map(it -> BankBook.builder()
                                .id(it.id())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
