package com.ravi.petclinic.repository;

import com.ravi.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/30/2020 */
public interface PetRepository extends CrudRepository<Pet, Long> {
}
