package com.dlucchesi.myglic.model;

import java.io.Serializable;

public interface Login extends Serializable {
    String getLogin();

    String getPasswd();

    void setLogin(String login);

    void setPasswd(String passwd);
}
