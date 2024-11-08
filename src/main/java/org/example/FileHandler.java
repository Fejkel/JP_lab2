package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandler {
    private final String absPath;

    public FileHandler(String absPath) {
        this.absPath = absPath;

    }

    public ArrayList<Guest> getGuestsList() throws FileNotFoundException
    {
        String readline;
        File inputFile = new File(this.absPath);
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Guest> guestsList = new ArrayList<>();
        int id = 1;

        System.out.println("Loaded data:");
        while (fileScanner.hasNextLine()) {
            readline = fileScanner.nextLine();
            try {
                System.out.println(readline);
                String temp = readline.split("\t")[1];
                ArrayList<String> possessed = new ArrayList<>(Arrays.asList(temp.toUpperCase().split(",")));

                temp = readline.split("\t")[2];
                ArrayList<String> sought = new ArrayList<>(Arrays.asList(temp.toUpperCase().split(",")));

                Guest guest = new Guest(possessed, sought, id);
                guestsList.add(guest);
                id++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid file format");
                System.exit(2);
            }
        }
        fileScanner.close();
        return guestsList;
    }
}