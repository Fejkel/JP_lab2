package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandler {
    private final String absPath;

    public FileHandler(String absPath){
        this.absPath = absPath;
        this.validFile();

    }
    public void validFile()
    {
        File inputFile = new File(absPath);
        try {
            Scanner fileScanner = new Scanner(inputFile);
            String readline;

            System.out.println("Wczytana lista:");
            while(fileScanner.hasNextLine())
            {
                readline = fileScanner.nextLine();
                System.out.println(readline);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file at path " + this.absPath);
        }
    }

    public ArrayList<Guest> getGuestsList() throws FileNotFoundException {

        String readline;
        File inputFile = new File(this.absPath);
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Guest> GuestsList = new ArrayList<>();

        while (fileScanner.hasNextLine())
        {
            readline = fileScanner.nextLine();

            String temp = readline.split("\t")[1];
            ArrayList<String> possessed = new ArrayList <>(Arrays.asList(temp.split(",")));

            temp = readline.split("\t")[2];
            ArrayList<String> sought = new ArrayList <>(Arrays.asList(temp.split(",")));

            Guest guest = new Guest();
            guest.setPossessed_attributes(possessed);
            guest.setSought_attributes(sought);
            GuestsList.add(guest);
        }
        fileScanner.close();
        return GuestsList;
    }
}
