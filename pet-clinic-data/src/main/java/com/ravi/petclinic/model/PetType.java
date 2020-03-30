package com.ravi.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;

    public String getType() {
        return name;
    }

    public void setType(String type) {
        this.name = type;
    }
}
