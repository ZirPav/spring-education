package com.zirpav.springeducation.api;

import com.zirpav.springeducation.model.ExternalInfo;

public interface Process {

    boolean run(ExternalInfo externalInfo);
}
