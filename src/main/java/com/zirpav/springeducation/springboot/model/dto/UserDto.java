package com.zirpav.springeducation.springboot.model.dto;

import com.zirpav.springeducation.springboot.validation.Create;
import com.zirpav.springeducation.springboot.validation.CustomEmail;
import com.zirpav.springeducation.springboot.validation.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Builder
public class UserDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    public Long id;
    @NotEmpty
    public String name;
    @CustomEmail
    public String email;
    @NotNull
    public List<BankBookDto> bankBooks;

}
