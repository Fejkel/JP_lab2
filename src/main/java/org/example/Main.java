package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHandler filehold = new FileHandler("data_input.txt");
        ArrayList<Guest> result = filehold.getGuestsList();
        System.out.println(result);
    }
}