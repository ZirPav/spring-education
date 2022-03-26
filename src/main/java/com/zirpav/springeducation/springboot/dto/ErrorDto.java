package com.zirpav.springeducation.springboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    public String info;
}
