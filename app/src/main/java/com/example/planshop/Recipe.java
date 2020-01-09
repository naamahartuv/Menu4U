package com.example.planshop;

import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String ingredients;
    private String directions;
    private String recipeAdmin;



    public Recipe() {

    }

    public Recipe(String recipeName, String ingredients, String directions, String recipeAdmin){
        this.recipeName = recipeName;
        this.directions = directions;
        this.ingredients = ingredients;
        this.recipeAdmin = recipeAdmin;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
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

    public String getRecipeAdmin() {
        return recipeAdmin;
    }

    public void setRecipeAdmin(String recipeAdmin) {
        this.recipeAdmin = recipeAdmin;
    }

    public String toString() {
        return this.recipeName;
    }


}
