package com.dlucchesi.myglic.model.imp;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_healthcheck")
@SequenceGenerator(name="un_seq", sequenceName="t_healthcheck_seq", allocationSize=1)
public class HealthcheckImp extends BasicMyglicEntityImp implements com.dlucchesi.myglic.model.Healthcheck {

    protected String status;
    protected Date created;
    protected Date lastCheck;


    @Override
    public String toString() {
        return "Healthcheck(" +
                "id: " + this.getId() +
                ", isDeleted: " + this.getIsDeleted() +
                ", isActive: " + this.getIsActive() +
                ", status: " + this.getStatus() +
                ", created: " + this.getCreated() +
                ", lastCheck: " + this.getLastCheck() +
                ")"
                ;
    }

}
