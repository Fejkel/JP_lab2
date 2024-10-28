package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHandler filehandler = new FileHandler("data_input.txt");
        ArrayList<Guest> result = filehandler.getGuestsList();
        Simulation sim = new Simulation(result);
        sim.startSimulation();
    }
}