package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminEventList extends AppCompatActivity {

    private DatabaseReference refEvents;
    private ListView listViewAdminEvent;
    private FirebaseAuth mAuth;
    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_list);

        events = new ArrayList<Event>();
        listViewAdminEvent = findViewById(R.id.adminEventListView);
        showEventList();


    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEvent.class);
        startActivity(intent);
    }

    public void showEventList() {

        refEvents = FirebaseDatabase.getInstance().getReference("Events");

        refEvents.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mAuth = FirebaseAuth.getInstance();
                String myEmail = mAuth.getCurrentUser().getEmail();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Event event = ds.getValue(Event.class);
                    if (event.getEventAdmin().equals(myEmail)) {
                        events.add(event);
                    } else {
                        for (String email : event.getParticipants()) {
                            if (email.equals(myEmail)) {
                                events.add(event);
                            }
                        }
                    }
                }

                ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(
                        AdminEventList.this,
                        android.R.layout.simple_list_item_1,
                        events);

                listViewAdminEvent.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
