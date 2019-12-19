package com.example.planshop;

public class Event {
    private String nameEvent;
    private Participants participants;
    private EventRecipes recipes;

    private Event(){

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
}
