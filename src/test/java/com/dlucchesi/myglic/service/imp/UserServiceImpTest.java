package com.dlucchesi.myglic.service.imp;

import com.dlucchesi.myglic.controller.imp.HealthcheckImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserServiceImp  userServiceImp;

    @DisplayName("Test Save User")
    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/v1/healthcheck")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.application").exists());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.application[*].employeeId").isNotEmpty());
    }
}
