package com.ravi.petclinic.service.map;

import com.ravi.petclinic.model.Visit;
import com.ravi.petclinic.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/30/2020 */
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Visit findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Visit save(Visit object) {

        if(object.getPet() == null ||
            object.getPet().getOwner() == null ||
                object.getPet().getId() == null ||
                    object.getPet().getOwner().getId() == null){
            throw new RuntimeException("Invalid Visit");
        }

        return super.save(object);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit obj) {
        super.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
