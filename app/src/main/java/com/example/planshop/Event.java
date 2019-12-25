package com.example.planshop;

import java.util.ArrayList;

public class Event {
    private String nameEvent;
    private ArrayList<String> participants;
    private EventRecipes recipes;
    private String eventAdmin;

    public Event(String name, ArrayList<String> participants, EventRecipes recipes, String eventAdmin) {
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

    public EventRecipes getRecipes() {
        return recipes;
    }

    public void setRecipes(EventRecipes recipes) {
        this.recipes = recipes;
    }

    public String toString() {
        return this.nameEvent;
    }
}
