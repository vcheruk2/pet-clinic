package com.ravi.petclinic.repository;

import com.ravi.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/30/2020 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
