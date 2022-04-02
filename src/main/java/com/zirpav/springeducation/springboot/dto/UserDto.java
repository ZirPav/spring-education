package com.zirpav.springeducation.springboot.dto;

import com.zirpav.springeducation.springboot.validation.Create;
import com.zirpav.springeducation.springboot.validation.CustomEmail;
import com.zirpav.springeducation.springboot.validation.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    public Integer id;
    @NotEmpty
    public String name;
    @CustomEmail
    public String email;

}
