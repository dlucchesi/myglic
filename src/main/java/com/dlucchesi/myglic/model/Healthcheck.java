package com.dlucchesi.myglic.model;

public interface Healthcheck extends BasicMyglicEntity {
    @Override
    String toString();

    String getStatus();

    java.util.Date getCreated();

    java.util.Date getLastCheck();

    void setStatus(String status);

    void setCreated(java.util.Date created);

    void setLastCheck(java.util.Date lastCheck);
}
