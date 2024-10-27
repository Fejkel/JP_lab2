package org.example;

import java.util.ArrayList;

public class Guest {
    private ArrayList<String> possessed_attributes;
    private ArrayList<String> sought_attributes;
    public Guest() {}


    //settery
    public void setPossessed_attributes(ArrayList<String> possessed_attributes) {
        this.possessed_attributes = possessed_attributes;
    }
    public void setSought_attributes(ArrayList<String> sought_attributes) {
        this.sought_attributes = sought_attributes;
    }
    //gettery
    public ArrayList<String> getPossessed_attributes() {
        return possessed_attributes;
    }
    public ArrayList<String> getSought_attributes() {
        return sought_attributes;
    }
}
