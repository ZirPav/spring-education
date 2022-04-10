package com.zirpav.springeducation.springboot.service;

import com.zirpav.springeducation.springboot.service.api.UserService;
import com.zirpav.springeducation.springboot.mappers.api.BankBookMapper;
import com.zirpav.springeducation.springboot.mappers.api.UserMapper;
import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import com.zirpav.springeducation.springboot.model.dto.UserDto;
import com.zirpav.springeducation.springboot.model.entity.User;
import com.zirpav.springeducation.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BankBookMapper bankBookMapper;
    private final UserMapper userMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            BankBookMapper bankBookMapper,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.bankBookMapper = bankBookMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAll() {
        return Optional.ofNullable(userRepository.findAll()).orElse(Collections.emptyList()).stream()
                .map(userMapper::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.convertToUserDto(userRepository.getById(id));
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.convertToUser(userDto);
        return userMapper.convertToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.id()).orElseThrow(() -> new RuntimeException("User not found!"));
        user.email(userDto.email());
        user.name(userDto.name());
        return userMapper.convertToUserDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<BankBookDto> getAllBankBookDtos(Long id) {
        return userRepository.getById(id).bankBooks().stream()
                .map(bankBookMapper::convertToBankBookDto)
                .collect(Collectors.toList());
    }
}
