package com.dlucchesi.myglic.model;

import java.io.Serializable;

public interface BasicMyglicEntity extends Serializable {
    boolean canEqual(Object obj);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    Long getId();

    Boolean getIsDeleted();

    Boolean getIsActive();

    void setId(Long id);

    void setIsDeleted(Boolean isDeleted);

    void setIsActive(Boolean isActive);
}
