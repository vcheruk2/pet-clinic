package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownerSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet.add(new Owner().builder().id(1L).build());
        ownerSet.add(new Owner().builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    /*@Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners"))
                .andExpect(model().attribute("owners", hasSize(2)));

        assertEquals("owners/owners", ownerController.listOwners(model));
        assertEquals(ownerService.findAll().size(), 2);
    }*/

    /*@Test
    void listOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners"))
                .andExpect(model().attribute("owners", hasSize(2)));

        assertEquals("owners/owners", ownerController.listOwners(model));
        assertEquals(ownerService.findAll().size(), 2);
    }*/

    @Test
    void notImplemented() throws Exception{
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));
        assertEquals(ownerController.notImplemented(), "notImplemented");
        verifyZeroInteractions(ownerService);
    }

    @Test
    void getOwnerDetailstest() throws Exception {

        // Given
        Owner owner = Owner.builder().id(2L).firstName("Ravi").build();

        when(ownerService.findById(anyLong())).thenReturn(owner);

        // when
        mockMvc.perform(get("/owners/2"))
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(2L))));

        // then
        verify(ownerService, times(1)).findById(anyLong());
    }
}