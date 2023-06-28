package org.florek81;

public abstract class Vehicle {
    private final String vehicleID;

    protected Vehicle(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleID() {
        return vehicleID;
    }
}