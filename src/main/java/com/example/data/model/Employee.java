package com.example.data.model;

import java.util.List;
import java.util.ArrayList;

public class Employee {

    private String id;
    private String name;
    private List<Address> addresses;

    public Employee() {
        addresses = new ArrayList<Address>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "{" + id + "::" + name + "}";
    }
}

