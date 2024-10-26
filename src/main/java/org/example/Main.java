package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHandler filehold = new FileHandler("data_input.txt");
        ArrayList<Guest> result = filehold.getGuestsList();
        result.forEach(object -> {
            System.out.print(object);
            System.out.print(object.getPossessed_attributes());
            System.out.println(object.getSought_attributes());
        });
    }
}