package com.ravi.petclinic.bootstrap;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.model.Vet;
import com.ravi.petclinic.service.OwnerService;
import com.ravi.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService){
        this.vetService = vetService;
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Mark");
        owner1.setLastName("Zack");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Satya");
        owner2.setLastName("Nad");
        ownerService.save(owner2);

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
