package org.example;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
    protected ArrayList<String> sough_attributes;
    protected ArrayList<Guest> guests;

    protected ArrayList<ArrayList<Guest>> population;

    protected ArrayList<ArrayList<Guest>> survivorPopulation;
    protected ArrayList<ArrayList<Guest>> childPopulation;

    private float bestCompatibility;
    protected ArrayList<Guest> bestResult;

    int population_size;

    public Population(ArrayList<ArrayList<Guest>> population, ArrayList<String> sough_attributes, ArrayList<Guest> guests, int population_size)
    {
        this.population = population;
        this.sough_attributes = sough_attributes;
        this.guests = guests;
        this.population_size = population_size;
    }


    private void exterminatePopulation()
    {
        ArrayList<ArrayList<Guest>> survivorPopulation = new ArrayList<>();
        float Compatibility = CalculatedCompatibility();
        for (int i = 0; i < population_size; i++)
        {
            if(CalculateCompatibility(population.get(i))>=Compatibility)
            {
                survivorPopulation.add(population.get(i));
            }
            if(bestCompatibility==CalculateCompatibility(population.get(i)))
            {
                bestResult = population.get(i);
            }
            this.survivorPopulation=survivorPopulation;
        }

    }

    private ArrayList<Guest> creatingChild()
    {
        Random rand = new Random();
        ArrayList<Guest> child = new ArrayList<>();

        ArrayList<Guest> parent1 = survivorPopulation.get(rand.nextInt(survivorPopulation.size()));
        ArrayList<Guest> parent2 = survivorPopulation.get(rand.nextInt(survivorPopulation.size()));
        while(parent1 == parent2) {
            parent2 = survivorPopulation.get(rand.nextInt(survivorPopulation.size()));
        }

        for (int i = 0; i < parent1.size(); i++) {
            Guest selectedGuest = rand.nextBoolean() ? parent1.get(i) : parent2.get(i);
            while (child.contains(selectedGuest)) {
                selectedGuest = guests.get(rand.nextInt(guests.size()));
            }
            child.add(selectedGuest);
        }

        Guest randomGuest = guests.get(rand.nextInt(guests.size()));
        while (child.contains(randomGuest)) {
            randomGuest = guests.get(rand.nextInt(guests.size()));
        }
        child.set(rand.nextInt(parent1.size()), randomGuest);

        return child;
    }

    private void creatingNewPopulation()
    {
        ArrayList<ArrayList<Guest>> childPopulation = new ArrayList<>();

        for (int i = 0; i < population_size; i++)
        {
            ArrayList<Guest> child = creatingChild();
            childPopulation.add(child);
        }
        this.childPopulation = childPopulation;
    }



    private float CalculateCompatibility(ArrayList<Guest> list_of_suggested_guest)
    {
        int counter=0;
        for (Guest guest : list_of_suggested_guest) {
            ArrayList<String> toCompare = guest.getPossessed_attributes();
            for (String s : toCompare) {
                if (sough_attributes.contains(s)) {
                    counter++;
                    break;
                }
            }
        }
        return (float) counter / sough_attributes.size();
    }

    private float CalculatedCompatibility()
    {
        ArrayList<Float> tableOfCompatibility = new ArrayList<>();

        population.forEach(n -> tableOfCompatibility.add(CalculateCompatibility(n)));
        Collections.sort(tableOfCompatibility);
        this.bestCompatibility = Collections.max(tableOfCompatibility);
        if(population_size <10)
        {
          return tableOfCompatibility.get(tableOfCompatibility.size()-(tableOfCompatibility.size()/2));
        }
        return tableOfCompatibility.get(tableOfCompatibility.size()-(tableOfCompatibility.size()*2/10));
    }


    public void startPopulation()
    {
        exterminatePopulation();
        creatingNewPopulation();
    }
    //getters
    public ArrayList<ArrayList<Guest>> getChildPopulation(){return childPopulation;}
    public ArrayList<Guest> getBestResult(){return bestResult;}
    public float getBestCompatibility(){return bestCompatibility;}
}