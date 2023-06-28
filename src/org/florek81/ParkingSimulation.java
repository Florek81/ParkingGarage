package org.florek81;

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
        Menu menu = new Menu();
        while (true) {
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

            menu.handleChoice(choice, parkingGarage);
        }
    }

    private static int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }
}