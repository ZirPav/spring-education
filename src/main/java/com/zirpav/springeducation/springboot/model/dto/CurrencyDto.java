package com.zirpav.springeducation.springboot.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDto {
    public Long id;
    public String name;
}
