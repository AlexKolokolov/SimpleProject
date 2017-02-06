package org.kolokolov.simpleproject.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ClientManager {
    
    private String name;
    
    {
        name = "Alex";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
