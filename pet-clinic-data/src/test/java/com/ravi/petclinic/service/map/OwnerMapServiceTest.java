package com.ravi.petclinic.service.map;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.model.Pet;
import com.ravi.petclinic.model.PetType;
import com.ravi.petclinic.service.PetService;
import com.ravi.petclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class OwnerMapServiceTest {

    public static Owner owner;
    public static Owner savedOwner, savedOwnerNoMock;
    public static OwnerMapService ownerMapService, ownerMapServiceNoMock;
    public static PetType petType;
    public static Pet pet;
    public final Long id = 1L;

    @Mock
    public static PetTypeService petTypeService;

    @Mock
    public static PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(new OwnerMapServiceTest());
        owner = new Owner();
        owner.setLastName("test Owner");

        petType = new PetType();
        //petType.setName("Doberman");
        //petType.setId(Long.valueOf(7));

        pet = new Pet();
        pet.setOwner(owner);
        pet.setPetType(petType);

        owner.getPets().add(pet);

        //when(petTypeService.save(petType)).thenReturn(petType);
        when(petService.save(pet)).thenReturn(pet);

        ownerMapService = new OwnerMapService(petTypeService, petService);
        ownerMapServiceNoMock = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapServiceNoMock.save(Owner.builder().lastName("test owner").id(id).build());
    }

    @Test
    void save() {
        savedOwner = ownerMapService.save(owner);
        assertNotNull(savedOwner);
        Mockito.verify(petService, times(1)).save(pet);
        //Mockito.verify(petTypeService, times(1)).save(pet.getPetType());

        //savedOwnerNoMock = ownerMapServiceNoMock.save(owner);
        //assertNotNull(savedOwnerNoMock);
    }

    @Test
    void findById() {
        assertEquals(savedOwner, ownerMapService.findById(Long.valueOf(1)));
        assertEquals(id, ownerMapServiceNoMock.findById(id).getId());
    }

    @Test
    void findByLastName() {
        //assertNull(ownerMapService.findByLastName("test Owner"));
        //assertEquals("test Owner", ownerMapServiceNoMock.findByLastName("test Owner"));
        assertEquals("test owner", ownerMapServiceNoMock.findByLastName("test owner").getLastName());
    }

    @Test
    void findAll() {
        assertEquals(ownerMapServiceNoMock.findAll().size(), 1);
    }

    @Test
    void deleteById() {
        ownerMapServiceNoMock.deleteById(id);

        assertEquals(ownerMapServiceNoMock.findAll().size(), 0);
    }

    @Test
    void delete() {
        ownerMapServiceNoMock.delete(ownerMapServiceNoMock.findById(id));

        assertEquals(ownerMapServiceNoMock.findAll().size(), 0);
    }
}