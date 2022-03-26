package com.zirpav.springeducation.springbase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExternalInfo {
    private final Integer id;
    private final String info;
}

