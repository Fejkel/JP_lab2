package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length == 0)
        {
            System.out.println("please use this .jar with with --help for instructions");
        }else if (args[0].equals("--help"))
        {
            System.out.println("""
                    to run the program properly type: 'java -jar [program path] [input file path]' in terminal
                    Correct file format:
                    [id]\t[possessed_attribute1,possessed_attribute2,...]\t[sough_attribute1,sough_attribute2,...]""");

        }
        else
        {
            FileHandler filehandler = new FileHandler(args[0]);
            ArrayList<Guest> result = filehandler.getGuestsList();
            Simulation sim = new Simulation(result);
            sim.startSimulation();
        }
    }
}