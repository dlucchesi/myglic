package com.dlucchesi.myglic.service.imp;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.model.imp.MeasureImp;
import com.dlucchesi.myglic.repository.MeasureImpRepository;
import com.dlucchesi.myglic.util.BasicEntityMyglicUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service("measureService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MeasureServiceImp implements com.dlucchesi.myglic.service.MeasureService {

    final MeasureImpRepository  measureImpRepository;

    
    @Override
    public Measure create(){
        return new MeasureImp();
    }

    @Override
    public Optional<Measure> find(Long id){
        Optional<Measure> ret = Optional.empty();
        Optional<MeasureImp> opt = measureImpRepository.findById(id);
        if (opt.isPresent()){
            MeasureImp tmp = opt.get();
            Measure entity = tmp;
            ret = Optional.of(entity);
        }
        return ret;
    }

    @Override
    public Optional<Measure> save(Measure instance){
        Optional<Measure> ret = Optional.empty();
        if (!isNull(instance)){
            MeasureImp measureImp = validateInstance(instance);
            if (!isNull(measureImp)){
                if (validateObj(measureImp)){
                    ret = Optional.of(measureImpRepository.save(measureImp));
                }
            } else {
                log.error("Instance error! Instance: {}", instance);
            }
        } else {
            log.warn("User empty!");
        }
        return ret;
    }

    
    @Override
    public Boolean delete(Measure entity, Boolean phys){
        Boolean ret = Boolean.TRUE;
        Boolean bPhys = Boolean.FALSE;
        if (!isNull(entity)){
            Optional<MeasureImp> opt = measureImpRepository.findById(entity.getId());
            if (opt.isPresent()) {
                if (!isNull(phys)){
                    bPhys = phys;
                } else {
                    log.warn("Boolean to physical delete is empty! Assuming FALSE");
                }
                MeasureImp measureImp = opt.get();
                if (!bPhys){
                    BasicEntityMyglicUtil.makeDeleted(measureImp);
                    measureImpRepository.save(measureImp);
                } else {
                    log.warn("** ATTENTION: Deleting physically! User: {}", entity);
                    measureImpRepository.delete(measureImp);
                }
            } else {
                log.error("User to delete, not found");
            }
        } else {
            log.warn("User empty!");
            ret = Boolean.FALSE;
        }
        return ret;
    }

    private MeasureImp validateInstance(Measure instance) {
        if (!isNull(instance) && instance instanceof MeasureImp){
            return (MeasureImp) instance;
        }
        return null;
    }

    private Boolean validateObj(MeasureImp imp) {
        Boolean ret = Boolean.TRUE;
        if (!isNull(imp)){
            Date dtEntry = imp.getDtEntry();
            if (isNull(dtEntry)){
                log.debug("Measure date empty! Measure {}", imp);
                ret = Boolean.FALSE;
            } else {
                Long measureEntry = imp.getMeasureEntry();
                if (isNull(measureEntry)){
                    log.debug("Measure entry empty! Measure {}", imp);
                    ret = Boolean.FALSE;
                } else {
                    User user = imp.getUser();
                    if (isNull(user)){
                        log.debug("Measure user empty! Measure {}", imp);
                        ret = Boolean.FALSE;
                    }
                }
            }
        }
        return ret;
    }
}
