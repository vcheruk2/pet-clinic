package com.ravi.petclinic.service;

import java.util.Set;

public interface CommonService<T, ID> {
    T findById(ID id);

    T save(T object);

    Set<T> findAll();

    void delete(T obj);

    void deleteById(ID id);
}
