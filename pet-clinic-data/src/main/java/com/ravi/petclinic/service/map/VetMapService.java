package com.ravi.petclinic.service.map;

import com.ravi.petclinic.model.Specialty;
import com.ravi.petclinic.model.Vet;
import com.ravi.petclinic.service.SpecialtyService;
import com.ravi.petclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (object.getSpecialties().size() > 0){
            object.getSpecialties().forEach(specialty -> {
                if (specialty.getId() == null){
                    Specialty savedSpeciality = specialtyService.save(specialty);
                    specialty.setId(savedSpeciality.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet obj) {
        super.delete(obj);
    }
}
