package com.zirpav.springeducation.springboot.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto implements Serializable {
    public Long id;
    public UserDto user;
    public String number;
    public BigDecimal amount;
    public CurrencyDto currency;
}
