package com.example.runningapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RunnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RunnerRepository runnerRepository;

    @BeforeEach
    public void setUp() {
        runnerRepository.deleteAll();
        Runner runner1 = new Runner("Nagy Anna", 30, "nő");
        Runner runner2 = new Runner("Kis Krisztina", 25, "nő");
        runnerRepository.save(runner1);
        runnerRepository.save(runner2);
    }

    @Test
    public void testGetAllRunners() throws Exception {
        mockMvc.perform(get("/runners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Nagy Anna"))
                .andExpect(jsonPath("$[1].name").value("Kis Krisztina"));
    }
}
