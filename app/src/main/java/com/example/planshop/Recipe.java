package com.example.planshop;

import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private ArrayList<String> ingredients;
    private String directions; //לחשוב אם זה צריך להיות סטרינג או arraylist
    private String eventAdmin;

    public Recipe() {

    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }


    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getEventAdmin() {
        return eventAdmin;
    }

    public void setEventAdmin(String eventAdmin) {
        this.eventAdmin = eventAdmin;
    }
}
