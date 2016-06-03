package com.martinchamarro.lazystorage.model;

import com.martinchamarro.lazystorage.annotation.Id;

public class Client {

    @Id private String passport;
    public String name;
    String surname;
    private int age;
    private Address address;

    public Client() {
        // Empty
    }

    public Client(String passport, String name, String surname, int age, Address address) {
        this.passport = passport;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public void setId(String id) {
        this.passport = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        if (getAge() != client.getAge()) return false;
        if (getPassport() != null ? !getPassport().equals(client.getPassport()) : client.getPassport() != null) return false;
        if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(client.getSurname()) : client.getSurname() != null) return false;
        return getAddress() != null ? getAddress().equals(client.getAddress()) : client.getAddress() == null;
    }

}
