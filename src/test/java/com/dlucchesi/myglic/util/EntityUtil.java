package com.dlucchesi.myglic.util;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.service.MeasureService;
import com.dlucchesi.myglic.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EntityUtil {
    final UserService userService;
    final MeasureService   measureService;

    public Measure createNewMeasure(User user) {
        SimpleDateFormat sDtF = new SimpleDateFormat("YYYYMMDD HHmmss");
        Date dt = new Date();

        Measure m = measureService.create();
        BasicEntityMyglicUtil.makeNew(m);
        m.setMeasureEntry(80L);
        m.setDtEntry(dt);
        m.setObs("Test: " + sDtF.format(dt));
        m.setUser(user);

        return m;
    }

    public User createNewUser() {
        User u = userService.create();
        BasicEntityMyglicUtil.makeNew(u);
        u.setLogin("test@test.com");
        u.setPasswd("654321");
        return u;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
