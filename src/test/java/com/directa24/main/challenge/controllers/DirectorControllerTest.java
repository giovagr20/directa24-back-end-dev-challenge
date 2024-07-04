package com.directa24.main.challenge.controllers;

import com.directa24.main.challenge.exceptions.BadRequestException;
import com.directa24.main.challenge.mock.MockData;
import com.directa24.main.challenge.services.DirectorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DirectorController.class)
class DirectorControllerTest {
    private static final String URL = "/api/v1/directors?threshold=%d";
    @MockBean private DirectorService directorService;

    @Autowired private MockMvc mockMvc;

    @Test
    void givenDirector_whenIsCalledAndThresholdIsMinorThan0_thenThrowException() throws Exception {
        final int threshold = 0;
        Mockito.when(directorService.getDirectorsByThreshold(threshold))
                .thenThrow(BadRequestException.class);

        mockMvc.perform(get(URL.formatted(threshold)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenDirector_whenIsCalledAndThresholdIsGreaterThan0_thenReturnList() throws Exception {
        final int threshold = 1;
        final List<String> directorsMock = MockData.getDirectorsMock();
        Mockito.when(directorService.getDirectorsByThreshold(threshold))
                .thenReturn(directorsMock);
        MvcResult result = mockMvc.perform(get(URL.formatted(threshold)))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = result.getResponse().getContentAsString();
        String expectedResponse = "[\"Martin Scorssece\",\"Christopher Nolan\"]";

        assertEquals(actualResponse, expectedResponse);
    }
}