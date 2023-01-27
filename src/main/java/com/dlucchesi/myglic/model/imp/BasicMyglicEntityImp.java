package com.dlucchesi.myglic.model.imp;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BasicMyglicEntityImp implements com.dlucchesi.myglic.model.BasicMyglicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "un_seq")
    @Column(nullable = false, unique = true, name = "id", updatable = false)
    protected Long id;
    protected Boolean isDeleted;
    protected Boolean isActive;

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof BasicMyglicEntityImp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicMyglicEntityImp that = (BasicMyglicEntityImp) o;
        return getId().equals(that.getId()) && getIsDeleted().equals(that.getIsDeleted()) && getIsActive().equals(that.getIsActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsDeleted(), getIsActive());
    }
}
