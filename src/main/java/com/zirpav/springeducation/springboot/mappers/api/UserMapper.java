package com.zirpav.springeducation.springboot.mappers.api;

import com.zirpav.springeducation.springboot.model.dto.UserDto;
import com.zirpav.springeducation.springboot.model.entity.User;

public interface UserMapper {

    UserDto convertToUserDto(User user);
    User convertToUser(UserDto userDto);

}
