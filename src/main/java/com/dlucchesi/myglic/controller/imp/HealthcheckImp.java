package com.dlucchesi.myglic.controller.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/healthcheck")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class HealthcheckImp implements com.dlucchesi.myglic.controller.Healthcheck {

    @Value("${myglic.status:NOK}")
    String appStatus;

    protected final com.dlucchesi.myglic.service.HealthcheckService healthcheckService;

    @Override
    @GetMapping
    public ResponseEntity checkAll(){
        log.debug("Complete HealthCheck started");
        Map<String, String> mapRet = new HashMap<>();
        checkAppRunning(mapRet);
//        lstRet.add(checkDbRunning());
        log.info("Complete HealthCheck ended. Status {}", mapRet);
        log.debug("Complete HealthCheck ended");

        return ResponseEntity.ok(mapRet);
    }

    private void checkAppRunning(Map<String, String> m) {
        m.put("application", appStatus);
        m.put("database", healthcheckService.checkDatabase());
    }


}
