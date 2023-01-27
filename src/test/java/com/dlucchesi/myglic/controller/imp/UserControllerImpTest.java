package com.dlucchesi.myglic.controller.imp;

import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.service.UserService;
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

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserControllerImpTest {

    static final String URI = "/v1/user";
    final MockMvc mockMvc;
    final EntityUtil    entityUtil;

    final UserService   userService;

    @DisplayName("Test save user")
    @Test
    void testSave() throws Exception {
        User u = entityUtil.createNewUser();

        mockMvc.perform(post(URI)
                    .content(EntityUtil.asJsonString(u))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("test@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
            ;
//                .andExpect(MockMvcResultMatchers.jsonPath("$.application[*].employeeId").isNotEmpty());
    }

    @DisplayName("Test get user")
    @Test
    void testGet() throws Exception {
        User newUser = entityUtil.createNewUser();
        Optional<User> optU = userService.save(newUser);

        if (optU.isPresent()) {
            User u = optU.get();
            mockMvc.perform(get("/v1/user/" + u.getId())
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.login").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("test@test.com"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(u.getId()))
            ;
        } else {
            log.error("User NOT saved! User {}", newUser);
        }
    }

    @DisplayName("Test get user by login")
    @Test
    void testGetByLogin() throws Exception {
        User newUser = entityUtil.createNewUser();
        String login = "logintest@test.com";
        newUser.setLogin(login);
        Optional<User> optU = userService.save(newUser);

        if (optU.isPresent()) {
            User u = optU.get();
            mockMvc.perform(get("/v1/user/login/" + u.getLogin())
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.login").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.login").value(login))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(u.getId()))
            ;
        } else {
            log.error("User NOT saved! User {}", newUser);
        }
    }

}
