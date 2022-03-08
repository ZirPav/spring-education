package com.zirpav.springeducation.model;

import com.zirpav.springeducation.api.ExternalService;
import com.zirpav.springeducation.api.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Flow {

    private final ExternalService externalServiceInfo;
    private final Process externalInfoProcess;

    public Flow(ExternalService externalServiceInfo, @Lazy Process externalInfoProcess) {
        this.externalServiceInfo = externalServiceInfo;
        this.externalInfoProcess = externalInfoProcess;
    }

    public void run(Integer id) {
        log.info("Запускаем метод run класса Flow c id={}", id);
        ExternalInfo externalInfo = externalServiceInfo.getExternalInfo(id);
        if (externalInfo != null) {
            log.info("ExternalInfo != null");
            boolean run = externalInfoProcess.run(externalInfo);
            log.info("Выполнили метод run сервиса {}, результат выполнения {}", externalInfoProcess.getClass().getSimpleName(), run);
        } else {
            log.info(String.valueOf(externalInfoProcess.getClass()));
        }
    }
}
