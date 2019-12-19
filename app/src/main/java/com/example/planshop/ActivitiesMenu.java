package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivitiesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_menu);
    }

    public void myEvent(View view) {
        Intent intent = new Intent(this, AdminEventList.class);
        startActivity(intent);
    }

    public void myRecipes (View view){
        Intent intent = new Intent(this, MyRecipes.class);
        startActivity(intent);
    }
}
