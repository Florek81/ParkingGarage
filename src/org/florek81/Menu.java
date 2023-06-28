package org.florek81;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public void handleChoice(int choice, ParkingGarage parkingGarage) {
        switch (choice) {
            case 1:
                // User input for the Vehicle Numberplate
                System.out.println("Auto einparken");
                Scanner carParking = new Scanner(System.in);
                System.out.print("Bitte geben Sie ihr Kennzeichen ein: ");
                String vehicleID = carParking.nextLine();

                // Check if the vehicle is already parked in the garage
                String assignedSpot = parkingGarage.getVehiclePosition(vehicleID);
                if (!assignedSpot.startsWith("Fahrzeug konnte nicht gefunden werden!")) {
                    System.out.println(assignedSpot);
                } else {
                    Vehicle car = new Car(vehicleID); // Create a new instance of the Car class

                    assignedSpot = parkingGarage.assignParkingSpot(car);

                    if (assignedSpot.startsWith("Keine freien ")) {
                        System.out.println(assignedSpot); // Print the result of the assignment
                    } else {
                        System.out.println(assignedSpot);
                    }
                }
                break;

            case 2:
                // User input for the Vehicle Numberplate
                System.out.println("Auto ausparken");
                Scanner carReleasing = new Scanner(System.in);
                System.out.print("Bitte geben Sie ihr Kennzeichen ein: ");
                vehicleID = carReleasing.nextLine();

                boolean vehicleExists = false;

                // Check if the vehicle ID is assigned and exists in the garage
                for (int floor = 0; floor < parkingGarage.numFloors; floor++) {
                    List<Vehicle> spots = parkingGarage.parkingSpots.get(floor);
                    for (int spot = 0; spot < spots.size(); spot++) {
                        Vehicle vehicle = spots.get(spot);
                        if (vehicle != null && vehicle.getVehicleID().equals(vehicleID)) {
                            if (vehicle instanceof Car) {
                                String released = parkingGarage.releaseParkingSpot(vehicle); // Release the parking spot

                                if (released != null) {
                                    if (released.startsWith("Es kann das Fahrzeug ")) {
                                        System.out.println(released); // Print the result of the release
                                    } else {
                                        spots.set(spot, null); // Set the spot to null
                                        System.out.println(released); // Print the result of the release
                                    }
                                } else {
                                    System.out.println("Beim Freigeben des Parkplatzes ist ein Fehler aufgetreten.");
                                }
                                vehicleExists = true;
                                break;
                            } else {
                                System.out.println("Das Fahrzeug mit dem Kennzeichen: " + vehicleID + " ist kein Auto.");
                            }
                        }
                    }
                    if (vehicleExists) {
                        break;
                    }
                }

                if (!vehicleExists) {
                    System.out.println("Das Fahrzeug mit dem Kennzeichen:" + vehicleID + " ist nicht in der Garage geparkt.");
                }

                break;

            case 3:
                // User input for the Vehicle Numberplate
                System.out.println("Motorrad einparken");
                Scanner motorcycleParking = new Scanner(System.in);
                System.out.print("Bitte geben Sie ihr Kennzeichen ein: ");
                vehicleID = motorcycleParking.nextLine();

                // Check if the vehicle is already parked in the garage
                assignedSpot = parkingGarage.getVehiclePosition(vehicleID);
                if (!assignedSpot.startsWith("Fahrzeug konnte nicht gefunden werden!")) {
                    System.out.println(assignedSpot);
                } else {
                    Vehicle motorcycle = new Motorcycle(vehicleID); // Create a new instance of the Car class

                    assignedSpot = parkingGarage.assignParkingSpot(motorcycle);

                    if (assignedSpot.startsWith("Keine freien ")) {
                        System.out.println(assignedSpot); // Print the result of the assignment
                    } else {
                        System.out.println(assignedSpot);
                    }
                }
                break;

            case 4:
                // User input for the Vehicle Numberplate
                System.out.println("Motorrad ausparken");
                Scanner motorcycleReleasing = new Scanner(System.in);
                System.out.print("Bitte geben Sie ihr Kennzeichen ein: ");
                vehicleID = motorcycleReleasing.nextLine();

                vehicleExists = false;

                // Check if the vehicle ID is assigned and exists in the garage
                for (int floor = 0; floor < parkingGarage.numFloors; floor++) {
                    List<Vehicle> spots = parkingGarage.parkingSpots.get(floor);
                    for (int spot = 0; spot < spots.size(); spot++) {
                        Vehicle vehicle = spots.get(spot);
                        if (vehicle != null && vehicle.getVehicleID().equals(vehicleID)) {
                            if (vehicle instanceof Car) {
                                String released = parkingGarage.releaseParkingSpot(vehicle); // Release the parking spot

                                if (released != null) {
                                    if (released.startsWith("Es kann das Fahrzeug ")) {
                                        System.out.println(released); // Print the result of the release
                                    } else {
                                        spots.set(spot, null); // Set the spot to null
                                        System.out.println(released); // Print the result of the release
                                    }
                                } else {
                                    System.out.println("Beim Freigeben des Parkplatzes ist ein Fehler aufgetreten.");
                                }
                                vehicleExists = true;
                                break;
                            } else {
                                System.out.println("Das Fahrzeug mit dem Kennzeichen: " + vehicleID + " ist kein Motorrad.");
                            }
                        }
                    }
                    if (vehicleExists) {
                        break;
                    }
                }

                if (!vehicleExists) {
                    System.out.println("Das Fahrzeug mit dem Kennzeichen:" + vehicleID + " ist nicht in der Garage geparkt.");
                }

                break;

            case 5:
                // User input for the Vehicle Numberplate
                System.out.println("Fahrzeug lokalisieren");
                Scanner parkedVehiclePosition = new Scanner(System.in);
                System.out.print("Bitte geben Sie ihr Kennzeichen ein: ");
                vehicleID = parkedVehiclePosition.nextLine();

                // Check if the vehicle is already parked in the garage
                assignedSpot = parkingGarage.getVehiclePosition(vehicleID);
                if (!assignedSpot.startsWith("Fahrzeug konnte nicht gefunden werden!")) {
                    System.out.println(assignedSpot);
                }
                break;

            case 6:
                // User input for the Vehicle Numberplate
                System.out.println("Freie Parkplätze");
                System.out.println("Es sind derzeitig " + parkingGarage.getFreeParkingSpots() + " Parkplätze verfügbar.");
                break;

            case 7:
                System.out.println("Vielen Dank für Ihren Besuch!");
                System.exit(0);
                break;
            default:
                System.out.println("Ungültige Eingabe. Bitte wählen Sie eine Zahl zwischen 1 und 7.");
                break;
        }
    }
}
