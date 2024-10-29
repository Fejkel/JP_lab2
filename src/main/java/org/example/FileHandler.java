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

            System.out.println("Loaded list:\n[id]\t[possessed_attributes]\t[sough_attributes]");
            while(fileScanner.hasNextLine())
            {
                readline = fileScanner.nextLine();
                System.out.println(readline);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file at path " + this.absPath);
            System.exit(1);
        }
    }

    public ArrayList<Guest> getGuestsList() throws FileNotFoundException {

        String readline;
        File inputFile = new File(this.absPath);
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Guest> GuestsList = new ArrayList<>();
        int id=1;
        while (fileScanner.hasNextLine())
        {
            readline = fileScanner.nextLine();
            try {
                String temp = readline.split("\t")[1];
                ArrayList<String> possessed = new ArrayList<>(Arrays.asList(temp.toUpperCase().split(",")));

                temp = readline.split("\t")[2];
                ArrayList<String> sought = new ArrayList<>(Arrays.asList(temp.toUpperCase().split(",")));

                Guest guest = new Guest(possessed, sought, id);
                GuestsList.add(guest);
                id++;
            }catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid file format");
                System.exit(2);
            }
        }
        fileScanner.close();
        return GuestsList;
    }
}
