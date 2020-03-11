package com.ravi.petclinic.service.map;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.service.CommonService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CommonService<Owner, Long> {
    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner obj) {
        super.delete(obj);
    }
}
