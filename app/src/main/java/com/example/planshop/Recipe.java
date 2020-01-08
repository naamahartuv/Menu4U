package com.example.planshop;

import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String ingredients;
    private String directions;
    private String recipeAdmin;



    public Recipe() {

    }

    public Recipe(String name, String ing, String dir, String rec){
        this.recipeName = name;
        this.directions = dir;
        this.ingredients = ing;
        this.recipeAdmin = rec;
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


}
