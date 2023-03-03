package com.dlucchesi.myglic.model.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageData {

    private String  message;

    public MessageData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MesageData{" +
                "message='" + message + '\'' +
                '}';
    }
}
