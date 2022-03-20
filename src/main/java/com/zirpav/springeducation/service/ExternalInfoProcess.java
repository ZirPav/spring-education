package com.zirpav.springeducation.service;

import com.zirpav.springeducation.annotation.CheckRequest;
import com.zirpav.springeducation.api.Process;
import com.zirpav.springeducation.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer idNotProcess;

    @CheckRequest
    @Override
    public boolean run(final ExternalInfo externalInfo) {
        log.info("Вызвали метод run с аргументом: {}", externalInfo);
        if (Objects.equals(idNotProcess, externalInfo.id())) {
            log.info("id-not-process {} == externalInfo.id {}, выбрасываем исключение", idNotProcess, externalInfo.id());
            throw new IllegalArgumentException("Некорректный id");
        }
        return true;
    }
}
