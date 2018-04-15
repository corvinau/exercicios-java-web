package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ConfigBean implements Serializable {

    private String email;
    
    public ConfigBean(){
        email = "batata@gmail.com";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
