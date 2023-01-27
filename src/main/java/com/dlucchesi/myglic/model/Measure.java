package com.dlucchesi.myglic.model;

import com.dlucchesi.myglic.model.imp.MeasureImp;
import com.dlucchesi.myglic.model.imp.UserImp;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MeasureImp.class, name = "measureImp")
})
@JsonDeserialize(as = MeasureImp.class)
public interface Measure extends BasicMyglicEntity {
    @Override
    boolean canEqual(Object obj);

    @Override
    String toString();

    java.util.Date getDtEntry();

    Long getMeasureEntry();

    String getObs();

    void setDtEntry(java.util.Date dtEntry);

    void setMeasureEntry(Long measureEntry);

    void setObs(String obs);

    User getUser();

    void setUser(User user);
}
