package com.example.planshop;

import java.util.ArrayList;

public class recipe {
    private ArrayList<Item> ingredients;
    private String preparation;


    public ArrayList<Item> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Item> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
