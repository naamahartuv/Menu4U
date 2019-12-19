package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class AddEvent extends AppCompatActivity implements PartDialog.PartDialogListener {

    private Participants participants;
    private AdminRecipes recipes;
    private ListView listViewPart;
    private ImageButton addButton;
    private DatabaseReference ref;



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
        final String temp = email;
//        Toast.makeText(getApplicationContext(), "len" , Toast.LENGTH_LONG).show();

        ref = FirebaseDatabase.getInstance().getReference().child("users").child("FIDlvFyuseVaTbu8EM5uDdGIxAF2");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ref = ref.child(dataSnapshot.);
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.child(temp).exists()){
                        User user = dataSnapshot.getValue(User.class);
                        participants.getEventUsers().add(user);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "user dosent exist", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(
                AddEvent.this,
                android.R.layout.simple_list_item_1,
                participants.getEventUsers());

        listViewPart.setAdapter(adapter);
    }
}
