package com.dlucchesi.myglic.model.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MeasureData implements Serializable {

    protected Long   id;
    protected Boolean isActive;
    protected Boolean isDeleted;
    protected Long measureEntry;
    protected String obs;
    protected Long user;
}
