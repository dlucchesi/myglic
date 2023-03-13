package com.dlucchesi.myglic.service.imp;

import com.dlucchesi.myglic.model.imp.HealthcheckImp;
import com.dlucchesi.myglic.repository.HealthcheckImpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service("healthcheckService")
@RequestMapping("/v1/healthcheck")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class HealthcheckServiceImp implements com.dlucchesi.myglic.service.HealthcheckService {

    protected final HealthcheckImpRepository healthcheckRepository;

    @Override
    public String checkDatabase() {
        Optional<HealthcheckImp> opt = healthcheckRepository.findAll().stream().findFirst();

        HealthcheckImp hc;
        if (!opt.isPresent()) {
            hc = createNewHealthcheck();
        } else {
            hc = opt.get();
            hc.setLastCheck(new java.util.Date());
        }
        HealthcheckImp fromDb = healthcheckRepository.save(hc);
        if (!isNull(fromDb) && !isNull(fromDb.getId())){
            return fromDb.getStatus();
        }
        return "NOK";
    }

    private HealthcheckImp createNewHealthcheck() {
        HealthcheckImp ret = new HealthcheckImp();
        Date dtNow = new Date();
        ret.setCreated(dtNow);
        ret.setLastCheck(dtNow);
        ret.setStatus("OK");
        return ret;
    }

}
