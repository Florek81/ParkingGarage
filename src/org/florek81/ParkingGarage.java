package org.florek81;

import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    final List<List<Vehicle>> parkingSpots;
    int numFloors;
    private int numParkingSpotsPerFloor;

    public ParkingGarage() {
        parkingSpots = new ArrayList<>();
    }

    // 
    public void resizeParkingGarage(int newNumFloors, int newNumParkingSpotsPerFloor) {
        numFloors = newNumFloors;
        numParkingSpotsPerFloor = newNumParkingSpotsPerFloor;

        // Resize the parking spots list
        while (parkingSpots.size() < numFloors) {
            parkingSpots.add(new ArrayList<>());
        }

        // Resize each floor to the desired number of parking spots
        for (List<Vehicle> floor : parkingSpots) {
            while (floor.size() < numParkingSpotsPerFloor) {
                floor.add(null);
            }
        }
    }

    // Assign a parking spot to the vehicle(Auto einparken, Motorrad einparken)
    public String assignParkingSpot(Vehicle vehicle) {
        for (int floor = 0; floor < numFloors; floor++) {
            List<Vehicle> spots = parkingSpots.get(floor);
            for (int spot = 0; spot < numParkingSpotsPerFloor; spot++) {
                if (spots.get(spot) == null) {
                    // Found an available spot, assign it to the vehicle
                    spots.set(spot, vehicle);
                    return vehicle.getVehicleID() + " wurde auf der Etage " + (floor + 1) + ", auf dem Parkplatz mit der Nummer: " + (spot + 1) + " zugewiesen."; // Parking spot assigned successfully
                }
            }
        }
        return "Keine freien Parkplätze für das Fahrzeug " + vehicle.getVehicleID() + " verfügbar!"; // No available parking spots
    }

    // Release the Vehicle from the parking spot pool(Auto ausparken)
    public String releaseParkingSpot(Vehicle vehicle) {
        for (int floor = 0; floor < numFloors; floor++) {
            List<Vehicle> spots = parkingSpots.get(floor);
            for (int spot = 0; spot < spots.size(); spot++) {
                if (spots.get(spot) == vehicle) {
                    // Release the parking spot by setting it to null
                    spots.set(spot, null);
                    return vehicle.getVehicleID() + " wurde auf der Etage " + (floor + 1) + ", auf dem Parkplatz mit der Nummer: " + (spot + 1) + " entfernt."; // Parking spot released successfully
                }
            }
        }
        return "Es kann das Fahrzeug mit dem Kennzeichen: " + vehicle.getVehicleID() + " nicht gefunden werden! Möglicherweise ist das Fahrzeug bereits ausgeparkt."; // Vehicle not found or already released
    }

    // Determine where your vehicle is parked(Parkplatz finden)
    public String getVehiclePosition(String vehicleID) {
        for (int floor = 0; floor < numFloors; floor++) {
            List<Vehicle> spots = parkingSpots.get(floor);
            for (int spot = 0; spot < numParkingSpotsPerFloor; spot++) {
                Vehicle vehicle = spots.get(spot);
                if (vehicle != null && vehicle.getVehicleID().equals(vehicleID)) {
                    return "Fahrzeug mit dem Kennzeichen: " + vehicleID + " wurde auf der Ebene: " + (floor + 1) + " und der Parkplatznummer: " + (spot + 1) + " gefunden!";
                }
            }
        }
        return "Fahrzeug konnte nicht gefunden werden!";
    }

    // Get the number of free ParkingSpots(Anzahl der freien Parkplätze)
    public int getFreeParkingSpots() {
        int freeSpots = 0;
        for (int floor = 0; floor < numFloors; floor++) {
            List<Vehicle> spots = parkingSpots.get(floor);
            for (int spot = 0; spot < numParkingSpotsPerFloor; spot++) {
                if (spots.get(spot) == null) {
                    freeSpots++;
                }
            }
        }
        return freeSpots;
    }
}