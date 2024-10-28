package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Simulation{
    private final ArrayList<Guest> guests;

    private static final int population_size = 300;
    private static final int max_generations = 10000;

    public Simulation(ArrayList<Guest> guests){
    this.guests = guests;
    }
    private void createGenerations()
    {
        Random rand = new Random();
        ArrayList<ArrayList<Guest>> solutionOfGuests=new ArrayList<>();
        int ids = guests.size();
        for(int id = 0; id < ids; id++)
        {
            int generation_no = 0;
            ArrayList<ArrayList<Guest>> fitting = new ArrayList<>();
            for(int i = 0; i < population_size; i++)
            {
                ArrayList<Guest> fitting_guest = new ArrayList<>();
                for(int j = 0; j < 5; j++) {
                    int random_parent = rand.nextInt(guests.size());
                    while(random_parent == 0)
                    {
                        random_parent = rand.nextInt(guests.size());
                    }
                    fitting_guest.add(guests.get(random_parent));
                }
                fitting.add(fitting_guest);
            }

            ArrayList<Guest> guestsWithoutSearchingOne = guests;
            guestsWithoutSearchingOne.removeFirst();

            ArrayList<Guest> tempSolution = new ArrayList<>();

            while (generation_no<max_generations)
            {
                Population population= new Population(fitting, guests.getFirst().getSought_attributes(),guestsWithoutSearchingOne, population_size);
                population.startPopulation();

                fitting.clear();
                fitting.addAll(population.getChildPopulation());

                tempSolution.clear();
                tempSolution.addAll(population.getBestResult());
                generation_no++;
            }
            solutionOfGuests.add(tempSolution);
        }
        System.out.println();
        for (ArrayList<Guest> solutionOfGuest : solutionOfGuests) {
            for (Guest guest : solutionOfGuest) {
                System.out.print(guest.getId()+", ");
            }
            System.out.println();
        }
    }

    
    public void startSimulation()
    {
        createGenerations();
    }
}

