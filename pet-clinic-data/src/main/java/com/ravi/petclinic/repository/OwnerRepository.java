package com.ravi.petclinic.repository;

import com.ravi.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/30/2020 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastNameLike);
}
