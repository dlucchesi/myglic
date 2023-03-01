package com.dlucchesi.myglic.model.imp;

import com.dlucchesi.myglic.model.Login;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginImp implements Login {

    protected String    login;
    protected String    passwd;

    @Override
    public String toString() {
        return "LoginImp{" +
                "login='" + login + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
