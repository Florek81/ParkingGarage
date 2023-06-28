package org.florek81;

import java.util.List;
import java.util.Scanner;

public class ParkingSimulation {
    public static void main(String[] args) {
        int choice;
        Scanner scanChoice = new Scanner(System.in);
        // Simulating with 10 Floors and 50 Spots per Floor
        int newNumFloors = 10;
        int newNumParkingSpotsPerFloor = 50;

        // Create an instance of ParkingGarage
        ParkingGarage parkingGarage = new ParkingGarage();

        // Resize the parking garage dynamically
        parkingGarage.resizeParkingGarage(newNumFloors, newNumParkingSpotsPerFloor);

        // Menu for the ParkingGarage
        do {

            System.out.println("""

                      ____               _     _                                  ____   _              _  _    __     __                     \s
                     |  _ \\  __ _  _ __ | | __| |__    __ _  _   _  ___          / ___| | |_  __ _   __| || |_  \\ \\   / /___  _ __    ___  ___\s
                     | |_) |/ _` || '__|| |/ /| '_ \\  / _` || | | |/ __|  _____  \\___ \\ | __|/ _` | / _` || __|  \\ \\ / // _ \\| '_ \\  / __|/ _ \\
                     |  __/| (_| || |   |   < | | | || (_| || |_| |\\__ \\ |_____|  ___) || |_| (_| || (_| || |_    \\ V /|  __/| | | || (__|  __/
                     |_|    \\__,_||_|   |_|\\_\\|_| |_| \\__,_| \\__,_||___/         |____/  \\__|\\__,_| \\__,_| \\__|    \\_/  \\___||_| |_| \\___|\\___|
                                                                                                                                              \s
                    """);
            System.out.println("1 - Auto einparken");
            System.out.println("2 - Auto ausparken");
            System.out.println("3 - Motorrad einparken");
            System.out.println("4 - Motorrad ausparken");
            System.out.println("5 - Parkplatz finden");
            System.out.println("6 - Freie Parkplätze");
            System.out.println("7 - Verlassen");
            String input = scanChoice.nextLine();
            choice = convertToInteger(input.trim());


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
        } while (true);
    }

    private static int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }
}