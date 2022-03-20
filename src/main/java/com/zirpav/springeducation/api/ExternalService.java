package com.zirpav.springeducation.api;

import com.zirpav.springeducation.model.ExternalInfo;

public interface ExternalService {

    ExternalInfo getExternalInfo(Integer id);
    String getSlogan();
}
