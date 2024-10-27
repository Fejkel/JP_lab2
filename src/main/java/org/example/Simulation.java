package org.example;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Simulation{
    private ArrayList<Guest> guests;
    private ArrayList<ArrayList<String>> list_of_possessed_attributes;
    private ArrayList<ArrayList<String>> list_of_sought_attributes;
    private static final int population_size = 100;
    private static final int max_generations = 1000;

    public Simulation(ArrayList<Guest> guests){
    this.guests = guests;
    }
    //Kryterium porównawczym będzie lista cech z którymi dana osoba chce się spotkać czym wiecej osób tego typu tym lepiej
    //schemat robienia algorytmu porównawczeog
    public void startSimulation()
    {
        SettingList();
        createGenerations();
    }
    private void SettingList(){
        ArrayList<ArrayList<String>> tempPossesed = new ArrayList<>();
        ArrayList<ArrayList<String>> tempSought = new ArrayList<>();

        guests.forEach(n -> {
            tempPossesed.add(n.getPossessed_attributes());
            tempSought.add(n.getSought_attributes());
        });
        setList_of_possessed_attributes(tempPossesed);
        setList_of_sought_attributes(tempSought);
    }
    private void createGenerations()
    {
        int compability = 0,generation_no = 0;
        ArrayList<ArrayList<String>> temp = new ArrayList<>();
        list_of_possessed_attributes.stream().skip(0).forEach(n -> temp.add(n));
        Population population= new Population(list_of_sought_attributes.get(0),temp,compability);
        while (generation_no<max_generations)
        {
            if(population.Compatibility == 1)
            {
                
                break;
            }
            generation_no++;
        }
    }

    private float CalculatedCompatibility()
    {
        return 0;
    }

//settery
    public void setList_of_possessed_attributes(ArrayList<ArrayList<String>> list_of_possessed_attributes) {
        this.list_of_possessed_attributes = list_of_possessed_attributes;
    }

    public void setList_of_sought_attributes(ArrayList<ArrayList<String>> list_of_sought_attributes) {
        this.list_of_sought_attributes = list_of_sought_attributes;
    }
//gettery
//    public ArrayList<ArrayList<String>> getList_of_possessed_attributes(){return list_of_possessed_attributes;}
//    public ArrayList<ArrayList<String>> getList_of_sought_attributes() {return list_of_sought_attributes;}
}

