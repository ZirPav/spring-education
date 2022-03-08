package com.zirpav.springeducation.service;

import com.zirpav.springeducation.annotation.CacheResult;
import com.zirpav.springeducation.api.ExternalService;
import com.zirpav.springeducation.model.CacheData;
import com.zirpav.springeducation.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ExternalServiceImpl implements ExternalService {

    private Map<Integer, ExternalInfo> data;

    private final CacheData cacheData;

    public ExternalServiceImpl(CacheData cacheData) {
        this.cacheData = cacheData;
    }

    @PostConstruct
    public void init() {
        data = new HashMap<>();
        data.put(1, ExternalInfo.builder().id(1).info(null).build());
        data.put(2, ExternalInfo.builder().id(2).info("hasInfo").build());
        data.put(3, ExternalInfo.builder().id(3).info("info").build());
        data.put(4, ExternalInfo.builder().id(4).info("information").build());
    }

    @CacheResult
    @Override
    public ExternalInfo getExternalInfo(final Integer id) {
        String key = this.getClass().getSimpleName() + "_" + "getExternalInfo" + "_" + "id" + "_" + id;
        ExternalInfo externalInfo = (ExternalInfo) cacheData.getCacheData().get(key);
        if (externalInfo != null) {
            log.info("Получили {} из кэша по ключу {}", externalInfo, key);
            return externalInfo;
        }
        log.info("Получаем ExternalInfo по id={}", id);
        return data.getOrDefault(id, null);
    }

    @CacheResult
    @Override
    public String getSlogan(final String str) {
        String key = this.getClass().getSimpleName() + "_" + "getSlogan" + "_" + "str" + "_" + str;
        String output = (String) cacheData.getCacheData().get(key);
        if (output != null) {
            log.info("Получили {} из кэша по ключу {}", output, key);
            return output;
        }
        return "Java: " + str;
    }

    @PreDestroy
    public void clean() {
        log.info("Запускаем очистку данных");
        data.clear();
    }
}
