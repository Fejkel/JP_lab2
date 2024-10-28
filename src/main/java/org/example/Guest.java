package org.example;

import java.util.ArrayList;

public class Guest {
    private final ArrayList<String> possessed_attributes;
    private final ArrayList<String> sought_attributes;
    private final int id;
    public Guest(ArrayList<String> possessed_attributes,ArrayList<String> sought_attributes,int id) {
        this.possessed_attributes = possessed_attributes;
        this.sought_attributes = sought_attributes;
        this.id = id;
    }


    //getters
    public ArrayList<String> getPossessed_attributes() {
        return possessed_attributes;
    }
    public ArrayList<String> getSought_attributes() {
        return sought_attributes;
    }
    public int getId() {return id;}
}
