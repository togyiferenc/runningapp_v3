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
public class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RaceRepository raceRepository;

    @BeforeEach
    public void setUp() {
        raceRepository.deleteAll();
        Race race1 = new Race("Spar BP Maraton", 42);
        Race race2 = new Race("Telekom Vivicitá", 21);
        raceRepository.save(race1);
        raceRepository.save(race2);
    }

    @Test
    public void testGetAllRaces() throws Exception {
        mockMvc.perform(get("/races"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Spar BP Maraton"))
                .andExpect(jsonPath("$[1].name").value("Telekom Vivicitá"));
    }
}
