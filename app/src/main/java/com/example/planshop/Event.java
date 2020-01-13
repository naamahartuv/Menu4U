package com.example.planshop;

import java.util.ArrayList;

public class Event {
    private String nameEvent;
    private ArrayList<String> participants, recipes;
    private String eventAdmin;

    public Event() {

    }

    public Event(String name, ArrayList<String> participants, ArrayList<String> recipes, String eventAdmin) {
        this.eventAdmin = eventAdmin;
        this.participants = participants;
        this.recipes = recipes;
        this.nameEvent = name;
    }

    public String getEventAdmin() {
        return eventAdmin;
    }

    public void setEventAdmin(String eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public ArrayList<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<String> recipes) {
        this.recipes = recipes;
    }

    public String toString() {
        return this.nameEvent;
    }
}
