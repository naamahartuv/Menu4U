package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class CurrentRecipe extends AppCompatActivity {
    private String nameRecipe, ingridients, directions;
    private TextView title;
    private MultiAutoCompleteTextView textRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_recipe);

        Intent myIntent = getIntent();
        nameRecipe = myIntent.getStringExtra("name");
        ingridients = myIntent.getStringExtra("ing");
        directions = myIntent.getStringExtra("dir");

        title = findViewById(R.id.title);
        title.setText(nameRecipe);

        textRecipe = findViewById((R.id.textRecipe));
        textRecipe.setText(ingridients + "\n" + directions );//לשנות את הסוג של הטקסט, ולחלק לשניים, לשים כותרות של מתכונים והוראות הכנה.

    }





}
