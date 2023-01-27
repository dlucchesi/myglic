package com.dlucchesi.myglic.model;

import com.dlucchesi.myglic.model.imp.UserImp;
import com.dlucchesi.myglic.util.json.MaskPasswd;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserImp.class, name = "userImp")
})
public interface User extends BasicMyglicEntity {
    @Override
    boolean canEqual(Object obj);

    @Override
    String toString();

    String getLogin();

    @MaskPasswd()
    String getPasswd();

    void setLogin(String login);

    void setPasswd(String passwd);

    @JsonIgnore
    java.util.Set<Measure> getMeasures();

    void setMeasures(java.util.Set<Measure> measures);
}
