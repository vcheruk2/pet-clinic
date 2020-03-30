package com.ravi.petclinic.bootstrap;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.model.Pet;
import com.ravi.petclinic.model.PetType;
import com.ravi.petclinic.model.Vet;
import com.ravi.petclinic.service.OwnerService;
import com.ravi.petclinic.service.PetTypeService;
import com.ravi.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Doggy");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner mark = new Owner();
        mark.setFirstName("Mark");
        mark.setLastName("Zack");
        mark.setAddress("Menlo Park Central");
        mark.setCity("Menlo Park");
        mark.setTelephone("111-111 1111");

        Pet marksPet = new Pet();
        marksPet.setName("facebook");
        marksPet.setOwner(mark);
        marksPet.setBirth(LocalDate.now());
        marksPet.setPetType(savedDogPetType);

        mark.getPets().add(marksPet);

        ownerService.save(mark);

        Owner satya = new Owner();
        satya.setFirstName("Satya");
        satya.setLastName("Nad");
        satya.setCity("Redmond");
        satya.setAddress("Redmond Central");
        satya.setTelephone("222-222 2222");

        Pet satyasPet = new Pet();
        satyasPet.setName("microsoft");
        satyasPet.setOwner(satya);
        satyasPet.setBirth(LocalDate.now());
        satyasPet.setPetType(savedCatPetType);

        satya.getPets().add(satyasPet);

        ownerService.save(satya);

        System.out.println("Loaded all owners..");

        Vet vet1 = new Vet();
        vet1.setFirstName("Gregg");
        vet1.setLastName("House");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Alison");
        vet2.setLastName("Cameron");
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Sasya");
        vet3.setLastName("Cherukuri");
        vetService.save(vet3);



        System.out.println("Loaded all vets..");
    }
}
