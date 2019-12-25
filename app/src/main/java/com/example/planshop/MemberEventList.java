package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemberEventList extends AppCompatActivity {

    private DatabaseReference refEventsMember;
    private ListView listViewMemberEvent;
    private FirebaseAuth mAuth;
    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_event_list);

        events = new ArrayList<Event>();
        listViewMemberEvent = findViewById(R.id.memberEventListView);
        showEventList();
    }

    public void showEventList(){
        refEventsMember = FirebaseDatabase.getInstance().getReference("Events");
        refEventsMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAuth = FirebaseAuth.getInstance();
                String myEmail = mAuth.getCurrentUser().getEmail();

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Event event = ds.getValue(Event.class);

                    ArrayList<String> participants = event.getParticipants();
                    for(String email : participants){
                        if(email.equals(myEmail)){
                            events.add(event);
                        }
                    }

                }

                ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(
                        MemberEventList.this,
                        android.R.layout.simple_list_item_1,
                        events);

                listViewMemberEvent.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
