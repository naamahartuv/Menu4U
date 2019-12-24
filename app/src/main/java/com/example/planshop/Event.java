package com.example.planshop;

public class Event {
    private String nameEvent;
    private Participants participants;
    private EventRecipes recipes;
    private String eventAdmin;

    public Event(String name, Participants participants, EventRecipes recipes, String eventAdmin) {
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

    public Participants getParticipants() {
        return participants;
    }

    public void setParticipants(Participants participants) {
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
