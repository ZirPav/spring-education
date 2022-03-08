package com.zirpav.springeducation.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@Order(1)
public class CacheData {

    private final Map<String, Object> cacheData = new ConcurrentHashMap<>();

    public Map<String, Object> getCacheData() {
        return cacheData;
    }

}
