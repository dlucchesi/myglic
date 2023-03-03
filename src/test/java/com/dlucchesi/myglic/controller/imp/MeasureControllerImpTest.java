package com.dlucchesi.myglic.controller.imp;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.service.MeasureService;
import com.dlucchesi.myglic.service.UserService;
import com.dlucchesi.myglic.util.BasicEntityMyglicUtil;
import com.dlucchesi.myglic.util.EntityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MeasureControllerImpTest {

    final MockMvc mockMvc;

    final MeasureService    measureService;
    final UserService   userService;
    final EntityUtil    entityUtil;
    
    private final String URI = "/v1/measure";

    @DisplayName("Test save measure")
    @Test
    void testSave() throws Exception {
        User tmpU = entityUtil.createNewUser();
        Optional<User> optUser = userService.save(tmpU);
        if (optUser.isPresent()) {
            User u = optUser.get();
            Measure m = entityUtil.createNewMeasure(u);

            mockMvc.perform(post(URI)
                        .content(EntityUtil.asJsonString(m))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.measureEntry").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.measureEntry").value(m.getMeasureEntry()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                ;
        } else {
            log.error("User NOT saved! User {}", tmpU);
        }
    }

    @DisplayName("Test get measure")
    @Test
    void testGet() throws Exception {
        User tmpU = entityUtil.createNewUser();
        Optional<User> optUser = userService.save(tmpU);

        if (optUser.isPresent()) {
            User u = optUser.get();
            Measure tmp = entityUtil.createNewMeasure(u);
            tmp.setMeasureEntry(95L);
            Optional<Measure> optM = measureService.save(tmp);
            if (optM.isPresent()) {
                Measure m = optM.get();
                mockMvc.perform(get(URI + "/" +  m.getId())
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.measureEntry").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.measureEntry").value(tmp.getMeasureEntry()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(tmp.getId()))
                ;
            } else {
                log.error("Measure NOT saved! Measure {}", tmp);
            }
        } else {
            log.error("User NOT saved! User {}", tmpU);
        }
    }

    @DisplayName("Test list all measures")
    @Test
    void testListAll() throws Exception {
        User tmpU = entityUtil.createNewUser();
        Optional<User> optUser = userService.save(tmpU);

        if (optUser.isPresent()) {
            User u = optUser.get();
            Measure tmp1 = entityUtil.createNewMeasure(u);
            tmp1.setMeasureEntry(95L);
            Optional<Measure> optM1 = measureService.save(tmp1);

            Measure tmp2 = entityUtil.createNewMeasure(u);
            tmp2.setMeasureEntry(105L);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, -1);
            tmp2.setDtEntry(calendar.getTime());
            BasicEntityMyglicUtil.makeInactive(tmp2);
            Optional<Measure> optM2 = measureService.save(tmp2);

            if (optM1.isPresent() && optM2.isPresent()) {
                mockMvc.perform(get(URI + "/userId/" + u.getId() + "/all")
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[*].measureEntry").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].isActive").value(Boolean.FALSE))
                ;
            } else {
                log.error("Measure NOT saved! Measure {}", tmp2);
            }
        } else {
            log.error("User NOT saved! User {}", tmpU);
        }
    }

    @DisplayName("Test list active measures")
    @Test
    void testList() throws Exception {
        User tmpU = entityUtil.createNewUser();
        Optional<User> optUser = userService.save(tmpU);

        if (optUser.isPresent()) {
            User u = optUser.get();
            Measure tmp1 = entityUtil.createNewMeasure(u);
            tmp1.setMeasureEntry(95L);
            Optional<Measure> optM1 = measureService.save(tmp1);

            Measure tmp2 = entityUtil.createNewMeasure(u);
            tmp2.setMeasureEntry(105L);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, -1);
            tmp2.setDtEntry(calendar.getTime());
            BasicEntityMyglicUtil.makeInactive(tmp2);
            Optional<Measure> optM2 = measureService.save(tmp2);

            if (optM1.isPresent() && optM2.isPresent()) {
                mockMvc.perform(get(URI + "/userId/" + u.getId())
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[*].measureEntry").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[*].isActive").value(Boolean.TRUE))
                ;
            } else {
                log.error("Measure NOT saved! Measure {}", tmp2);
            }
        } else {
            log.error("User NOT saved! User {}", tmpU);
        }
    }

    @DisplayName("Test get measure by user login")
    @Test
    void testGetByLogin() throws Exception {
        User tmpU = entityUtil.createNewUser();
        Optional<User> optUser = userService.save(tmpU);
        if (optUser.isPresent()) {
            User u = optUser.get();

            Measure tmp = entityUtil.createNewMeasure(u);
            tmp.setMeasureEntry(90L);
            Optional<Measure> optM = measureService.save(tmp);
            if (optM.isPresent()) {

                mockMvc.perform(get(URI + "/userId/" + u.getId())
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.login").exists())
                ;
            } else {
                log.error("Measure NOT saved! Measure {}", tmp);
            }
        } else {
            log.error("User NOT saved! User {}", tmpU);
        }
    }

    @DisplayName("Test inactivate measure")
    @Test
    void testInactivate() throws Exception {
        User tmpU = entityUtil.createNewUser();
        Optional<User> optUser = userService.save(tmpU);
        if (optUser.isPresent()) {
            User u = optUser.get();
            Measure m = entityUtil.createNewMeasure(u);
            Optional<Measure> optRetM = measureService.save(m);
            if (optRetM.isPresent()) {
                Measure retM = optRetM.get();
                mockMvc.perform(post(URI + "/inactivate/" + retM.getId())
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Inactivated!"))
                ;
            } else {
                log.error("Measure NOT saved! Measure {}", m);
            }

        } else {
            log.error("User NOT saved! User {}", tmpU);
        }
    }

}
