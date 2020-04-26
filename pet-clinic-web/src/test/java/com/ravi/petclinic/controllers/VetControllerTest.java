package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Vet;
import com.ravi.petclinic.service.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void listVets() throws Exception {
        // given
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(Vet.builder().build());
        vetSet.add(Vet.builder().build());

        when(vetService.findAll()).thenReturn(vetSet);

        // when
        mockMvc.perform(get("/vet"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/vets"))
                .andExpect(model().attributeExists("vets"))
                .andExpect(model().attribute("vets", hasSize(2)));

        // then
        verify(vetService, times(1)).findAll();
    }
}