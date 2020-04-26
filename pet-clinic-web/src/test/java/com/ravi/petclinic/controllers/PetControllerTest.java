package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.model.Pet;
import com.ravi.petclinic.model.PetType;
import com.ravi.petclinic.service.OwnerService;
import com.ravi.petclinic.service.PetService;
import com.ravi.petclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Collection<PetType> petTypes;
    Pet pet;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        owner = Owner.builder().id(2L).build();

        pet = new Pet();
        pet.setId(2L);

        pet.setOwner(owner);

        petTypes = new HashSet<>();

        PetType petType1 = new PetType();
        petType1.setId(1L);
        petType1.setName("petType1");

        PetType petType2 = new PetType();
        petType2.setId(2L);
        petType2.setName("petType2");

        petTypes.add(petType1);
        petTypes.add(petType2);
    }

    @Test
    void initNewPetForm() throws Exception {
        // given
        when(petTypeService.findAll()).thenReturn((Set<PetType>) petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        // when
        mockMvc.perform(get("/owners/2/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("/pets/createOrUpdatePetForm"));

        // then
        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processNewPetForm() throws Exception {
        // given
        when(petTypeService.findAll()).thenReturn((Set<PetType>) petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        // when
        mockMvc.perform(post("/owners/2/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"))
                .andExpect(model().attributeExists("owner"));

        // then
        verify(petService).save(any());
    }

    @Test
    void initUpdatePetForm() throws Exception {
        // given
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn((Set<PetType>) petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());

        // then
        mockMvc.perform(get("/owners/2/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("/pets/createOrUpdatePetForm"));

        // then
        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void processUpdatePetForm() throws Exception {
        // given
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.save(any())).thenReturn(pet);

        // then
        mockMvc.perform(post("/owners/2/pets/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/2"));

        // then
        verify(petService, times(1)).save(any());
    }
}