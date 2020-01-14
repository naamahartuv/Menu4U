package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class currentEvent extends AppCompatActivity {
    private String eventName;
    private ArrayList<String> recipeList;
    private TextView title;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_event);

        Intent intent = getIntent();
        eventName = intent.getStringExtra("name");
        recipeList = intent.getStringArrayListExtra("list");

        title = findViewById(R.id.textView8);
        title.setText(eventName);


        list = findViewById(R.id.listRecipe);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                currentEvent.this,
                android.R.layout.simple_list_item_1,
                recipeList
        );
        list.setAdapter(adapter);
    }
}
