package com.ravi.petclinic.service.springdatajpa;

import com.ravi.petclinic.model.Specialty;
import com.ravi.petclinic.repository.SpecialtyRepository;
import com.ravi.petclinic.service.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/30/2020 */
@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty findById(Long aLong) {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(aLong);
        if (optionalSpecialty.isPresent())
            return optionalSpecialty.get();
        else
            return null;
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialtySet = new HashSet<>();
        specialtyRepository.findAll().forEach(specialtySet::add);
        return specialtySet;
    }

    @Override
    public void delete(Specialty obj) {
        specialtyRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
