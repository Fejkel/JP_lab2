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
            ArrayList<String> possessed = new ArrayList <>();
            ArrayList<String> sought = new ArrayList<>();

            readline = fileScanner.nextLine();

            String temp = readline.split("\t")[1];
            possessed.add(Arrays.toString(temp.split(",")));
            temp = readline.split("\t")[2];
            sought.add(Arrays.toString(temp.split(",")));

            Guest guest = new Guest();
            guest.setPossessed_attributes(possessed);
            guest.setSought_attributes(sought);
            GuestsList.add(guest);
        }
        return GuestsList;
    }
}
