package com.zirpav.springeducation.springboot.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto implements Serializable {
    public Integer id;
    public Integer userId;
    public String number;
    public BigDecimal amount;
    public String currency;
}
