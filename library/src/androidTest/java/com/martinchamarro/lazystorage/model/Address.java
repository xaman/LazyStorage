package com.martinchamarro.lazystorage.model;

public class Address {

    private int id;
    private String street;
    private int number;
    private String postalCode;
    private int floor;
    private String city;
    private String county;
    private String state;
    private String country;

    public Address() {
        // Empty
    }

    public Address(int id, String street, int number, String postalCode, int floor, String city, String county, String state, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.floor = floor;
        this.city = city;
        this.county = county;
        this.state = state;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        if (getId() != address.getId()) return false;
        if (getNumber() != address.getNumber()) return false;
        if (getFloor() != address.getFloor()) return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        if (getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null) return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (getCounty() != null ? !getCounty().equals(address.getCounty()) : address.getCounty() != null) return false;
        if (getState() != null ? !getState().equals(address.getState()) : address.getState() != null) return false;
        return getCountry() != null ? getCountry().equals(address.getCountry()) : address.getCountry() == null;
    }

}
