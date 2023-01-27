package com.dlucchesi.myglic.controller.imp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthcheckImpTest {

//    @LocalServerPort
//    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    HealthcheckImp  healthcheckImp;

    @DisplayName("Test MyGlic Healthcheck")
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
