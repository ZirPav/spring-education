package com.zirpav.springeducation.springbase.api;

import com.zirpav.springeducation.springbase.model.ExternalInfo;

public interface ExternalService {

    ExternalInfo getExternalInfo(Integer id);
    String getSlogan();
}
