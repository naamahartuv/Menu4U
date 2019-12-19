package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class AddEvent extends AppCompatActivity implements PartDialog.PartDialogListener {

    private Participants participants;
    private AdminRecipes recipes;
    private ListView listViewPart;
    private ImageButton addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        listViewPart = findViewById(R.id.part);
        addButton = findViewById(R.id.imageButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        PartDialog partDialog = new PartDialog();
        partDialog.show(getSupportFragmentManager(), "Part dialog");
    }

    @Override
    public void applyText(String email) {
        //maybe we need to add it ti our array list of particioants and then add the list itself to the listview????????
//        participants.getEventUsers().add();
        listViewPart.setFilterText(email);
    }
}
