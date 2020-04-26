package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

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
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        *//*assertEquals("owners/owners", ownerController.listOwners(model));
        assertEquals(ownerService.findAll().size(), 2);*//*
    }*/

    @Test
    void findOwners() throws Exception{
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

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

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                    Owner.builder().id(2l).build()));

        // when
        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(2L).build()));

        // when
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"));
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(2L).build());

        // when
        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(2L))));
    }

    @Test
    void testEmptyOwnerReturnAll() throws Exception {
        String emptyString = "";
        when(ownerService.findAllByLastNameLike(emptyString)).thenReturn(Arrays.asList(Owner.builder().id(2L).build(),
                    Owner.builder().id(3L).build()));

        // when
        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void initCreationForm() throws Exception {
        // when
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        // then
        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        // when
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(2L).build());

        // when
        mockMvc.perform(get("/owners/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        // then
        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processUpdateForm() throws Exception {
        // given
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(2L).firstName("Test").lastName("Owner").build());

        // when
        mockMvc.perform(post("/owners/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"))
                .andExpect(model().attributeExists("owner"));

        // then
        verify(ownerService, times(1)).save(ArgumentMatchers.any());
    }
}