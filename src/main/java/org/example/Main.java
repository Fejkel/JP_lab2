package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static boolean isnotInt(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    public static void main(String[] args) {
        try {
            validateArguments(args);
            int population = Integer.parseInt(args[1]);
            int max_generation = Integer.parseInt(args[2]);

            FileHandler filehandler = new FileHandler(args[0]);
            ArrayList<Guest> result = filehandler.getGuestsList();

            Simulation sim = new Simulation(result, population, max_generation);
            sim.startSimulation();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            System.exit(1);
        } catch (InvalidArgumentsException e) {
            System.err.println("Arguments error: " + e.getMessage());
            printHelp();
            System.exit(1);
        }
    }

    private static void validateArguments(String[] args) throws InvalidArgumentsException {
        if (args.length == 1 && args[0].equals("--help")) {
            printHelp();
            System.exit(0);
        }

        if (args.length != 3) {
            throw new InvalidArgumentsException("Exactly 3 arguments are required");
        }

        if (isnotInt(args[1])) {
            throw new InvalidArgumentsException("Population size must be an integer");
        }

        if (isnotInt(args[2])) {
            throw new InvalidArgumentsException("Maximum generations must be an integer");
        }

        int population = Integer.parseInt(args[1]);
        int max_generation = Integer.parseInt(args[2]);

        if (population <= 3) {
            throw new InvalidArgumentsException("Population size must be greater than 3");
        }

        if (max_generation <= 0) {
            throw new InvalidArgumentsException("Maximum generations must be greater than 0");
        }
    }

    private static void printHelp() {
        System.out.println("""
            to run the program properly type: 'java -jar [program path] [input file path] [population size] [max generations] ' in terminal
            Correct file format:
            [id]\t[possessed_attribute1,possessed_attribute2,...]\t[sough_attribute1,sough_attribute2,...]""");
    }
}