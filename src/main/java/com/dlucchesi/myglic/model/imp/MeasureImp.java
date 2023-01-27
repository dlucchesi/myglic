package com.dlucchesi.myglic.model.imp;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_measure")
@SequenceGenerator(name="un_seq", sequenceName="t_measure_seq", allocationSize=1)
public class MeasureImp extends BasicMyglicEntityImp implements com.dlucchesi.myglic.model.Measure {

    @Column(nullable = false, unique = true, updatable = false, name = "date_entry")
    protected Date  dtEntry;
    @Column(nullable = false, name = "measure_entry")
    protected Long  measureEntry;
    @Column
    protected String    obs;
    @ManyToOne(targetEntity = UserImp.class, optional = false)
    protected User  user;

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Measure);
    }

    @Override
    public String toString() {
        return "Measure(" +
                "id: " + this.getId() +
                ", isDeleted: " + this.getIsDeleted() +
                ", isActive: " + this.getIsActive() +
                ", dtEntry: " + this.getDtEntry() +
                ", measureEntry: " + this.getMeasureEntry() +
                ", obs: " + this.getObs() +
                ")"
                ;
    }
}
