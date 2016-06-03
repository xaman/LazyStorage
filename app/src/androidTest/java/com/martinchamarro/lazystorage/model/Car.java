package com.martinchamarro.lazystorage.model;

import com.martinchamarro.lazystorage.annotation.Id;

public class Car {

    @Id private String registration;
    private String brand;
    private String model;
    private int seatsNumber;
    private float maxSpeed;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        if (getSeatsNumber() != car.getSeatsNumber()) return false;
        if (Float.compare(car.getMaxSpeed(), getMaxSpeed()) != 0) return false;
        if (getRegistration() != null ? !getRegistration().equals(car.getRegistration()) : car.getRegistration() != null) return false;
        if (getBrand() != null ? !getBrand().equals(car.getBrand()) : car.getBrand() != null) return false;
        return getModel() != null ? getModel().equals(car.getModel()) : car.getModel() == null;
    }
}
