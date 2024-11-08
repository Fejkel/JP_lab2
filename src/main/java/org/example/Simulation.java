package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Simulation{
    private final ArrayList<Guest> guests;

    private final int population_size;
    private final int max_generations;

    private ArrayList<ArrayList<Guest>> solution;

    public Simulation(ArrayList<Guest> guests, int population_size, int max_generations){
        this.guests = guests;
        this.population_size = population_size;
        this.max_generations = max_generations;
    }
    private void createGenerations()
    {
        ArrayList<ArrayList<Guest>> solutionOfGuests=new ArrayList<>();
        for (Guest searchingGuest : guests)
        {
            int generation_no = 0;
            ArrayList<ArrayList<Guest>> firstInitialized = firstInitialize(searchingGuest);

            ArrayList<Guest> guestsWithoutSearching = new ArrayList<>(guests);
            guestsWithoutSearching.remove(searchingGuest);

            ArrayList<Guest> tempSolution = new ArrayList<>();
            float bestCompatibility=0;
            int repeated = 0;
            while (generation_no < max_generations)
            {
                Population population = new Population(firstInitialized, searchingGuest.getSought_attributes(), guestsWithoutSearching, population_size);
                population.startPopulation();

                firstInitialized.clear();
                firstInitialized.addAll(population.getChildPopulation());

                tempSolution.clear();
                tempSolution.addAll(population.getBestResult());

                generation_no++;

                if(bestCompatibility == population.getBestCompatibility())
                {
                    repeated++;
                    if(repeated>=10)
                    {
                        solutionOfGuests.add(tempSolution);
                        break;
                    }
                }else if(bestCompatibility != population.getBestCompatibility())
                {
                    bestCompatibility = population.getBestCompatibility();
                    repeated=0;
                }
                if(generation_no == max_generations) solutionOfGuests.add(tempSolution);
            }
        }

        this.solution=solutionOfGuests;
    }

    private ArrayList<ArrayList<Guest>> firstInitialize(Guest searchingGuest){
        Random rand = new Random();
        ArrayList<ArrayList<Guest>> fitting = new ArrayList<>();

        for(int i = 0; i < population_size; i++)
        {
            ArrayList<Guest> fitting_guest = new ArrayList<>();
            while(fitting_guest.size() < 5)
            {
                int random_index = rand.nextInt(guests.size());
                Guest selectedGuest = guests.get(random_index);

                if (!selectedGuest.equals(searchingGuest) && !fitting_guest.contains(selectedGuest))
                {
                    fitting_guest.add(selectedGuest);
                }
            }
            fitting.add(fitting_guest);
        }
        return fitting;
    }
    private void printSolution()
    {
        int i=1;
        System.out.println("Result:");
        for (ArrayList<Guest> solutionOfGuest : solution)
        {
            System.out.print(i+". -> ");
            for (Guest guest : solutionOfGuest)
            {
                System.out.print(guest.getId()+"."+guest.getPossessed_attributes()+" ");
            }
            System.out.println();
            i++;
        }
    }
    
    public void startSimulation()
    {
        createGenerations();
        printSolution();
    }
}