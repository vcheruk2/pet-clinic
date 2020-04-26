package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Pet;
import com.ravi.petclinic.service.PetService;
import com.ravi.petclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        // given
        Pet pet = new Pet();
        pet.setId(2L);
        //when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());
        when(petService.findById(anyLong())).thenReturn(pet);
    }

    @Test
    void initNewVisitForm() throws Exception {
        // when
        mockMvc.perform(get("/owners/1/pets/2/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

        // Then
        verify(petService,times(1)).findById(anyLong());
    }

    @Test
    void processNewVisitForm() throws Exception {
        // when
        mockMvc.perform(post("/owners/1/pets/2/visits/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        // Then
        verify(petService,times(1)).findById(anyLong());
        verify(visitService, times(1)).save(any());
    }
}