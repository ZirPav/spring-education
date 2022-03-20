package com.zirpav.springeducation.service;

import com.zirpav.springeducation.annotation.CacheResult;
import com.zirpav.springeducation.api.ExternalService;
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
        log.info("Получаем ExternalInfo по id={}", id);
        ExternalInfo externalInfo = data.get(id);
        if (externalInfo == null) {
            throw new RuntimeException("Не найдено!");
        }
        return externalInfo;
    }

    @CacheResult
    @Override
    public String getSlogan() {
        return "ohhhh";
    }

    @PreDestroy
    public void clean() {
        log.info("Запускаем очистку данных");
        data.clear();
        log.info("Выполнили очистку данных");
    }
}
