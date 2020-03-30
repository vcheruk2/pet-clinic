package com.ravi.petclinic.service.map;

import com.ravi.petclinic.model.PetType;
import com.ravi.petclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/29/2020 */
@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(PetType obj) {
        super.delete(obj);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
