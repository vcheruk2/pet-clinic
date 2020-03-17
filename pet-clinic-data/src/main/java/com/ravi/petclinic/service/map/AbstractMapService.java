package com.ravi.petclinic.service.map;

import com.ravi.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){

        if(object != null){
            if(object.getId() == null)
                object.setId(getNextId());

            map.put(object.getId(), object);
        } else{
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    private Long getNextId(){
        Long nextId = null;

        try{
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e){
            nextId = 1L;
        }

        return nextId;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T obj){
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(obj));
    }
}
