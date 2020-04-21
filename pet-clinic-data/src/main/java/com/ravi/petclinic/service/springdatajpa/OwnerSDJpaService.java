package com.ravi.petclinic.service.springdatajpa;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.repository.OwnerRepository;
import com.ravi.petclinic.repository.PetRepository;
import com.ravi.petclinic.repository.PetTypeRepository;
import com.ravi.petclinic.service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/30/2020 */
@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastNameLike) {
        return ownerRepository.findAllByLastNameLike(lastNameLike);
    }

    @Override
    public Owner findById(Long aLong) {
        Optional<Owner> owner = ownerRepository.findById(aLong);

        if (owner.isPresent()) {
            return owner.get();
        }
        else{
            System.out.println("Unable to find the Owner");
            return null;
        }
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void delete(Owner obj) {
        ownerRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
