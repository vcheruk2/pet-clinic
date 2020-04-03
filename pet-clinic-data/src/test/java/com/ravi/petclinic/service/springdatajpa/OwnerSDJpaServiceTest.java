package com.ravi.petclinic.service.springdatajpa;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.repository.OwnerRepository;
import com.ravi.petclinic.repository.PetRepository;
import com.ravi.petclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    String lastName = "Cherukuri";
    Owner ravi;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        ravi = Owner.builder().id(1L).lastName(lastName).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(ravi);
        Owner savedOwner = service.findByLastName(lastName);

        assertEquals(lastName, savedOwner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(ravi));
        Owner foundUser = service.findById(1L);
        assertNotNull(foundUser);
        assertEquals(ravi.getLastName(), foundUser.getLastName());
        verify(ownerRepository).findById(any());
    }

    @Test
    void save() {
        Owner temp = new Owner();
        when(ownerRepository.save(temp)).thenReturn(ravi);

        assertEquals(service.save(temp).getLastName(), ravi.getLastName());
        verify(ownerRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(ravi);
        ownerSet.add(new Owner().builder().id(2L).build());
        ownerSet.add(new Owner().builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);
        assertEquals(service.findAll().size(), ownerSet.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        service.delete(ravi);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}