package com.dlucchesi.myglic.model.imp;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_user")
@SequenceGenerator(name="un_seq", sequenceName="t_user_seq", allocationSize=1)
public class UserImp extends BasicMyglicEntityImp implements com.dlucchesi.myglic.model.User {

    @Column(nullable = false, unique = true, updatable = false)
    protected String  login;
    @Column(nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String  passwd;
    @OneToMany(targetEntity = MeasureImp.class, mappedBy = "user")
    protected Set<Measure>  measures;

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof User);
    }

    @Override
    public String toString() {
        return "User(" +
                "id: " + this.getId() +
                ", isDeleted: " + this.getIsDeleted() +
                ", isActive: " + this.getIsActive() +
                ", login: " + this.getLogin() +
                ", passwd: " + "*****" +
                ")"
                ;
    }
}
