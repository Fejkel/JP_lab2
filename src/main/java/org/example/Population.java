package org.example;

import java.util.ArrayList;

public class Population {
    ArrayList<String> sought;
    ArrayList<ArrayList<String>> list_of_possesed;

    ArrayList<ArrayList<String>> list_of_best_compability;
    int Compatibility;
    public Population(ArrayList<String> sought, ArrayList<ArrayList<String>> list_of_possesed,int comatibility){
        this.sought=sought;
    }
    


    private float CalculatedCompatibility()
    {
        long searching = sought.stream().count();

        return searching;//nieskonczone
    }
}
