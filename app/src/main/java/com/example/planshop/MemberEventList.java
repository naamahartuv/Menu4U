package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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


        listViewMemberEvent = findViewById(R.id.memberEventListView);
        showEventList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, MemberEventList.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent2 = new Intent(this, ContactUs.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showEventList(){
        refEventsMember = FirebaseDatabase.getInstance().getReference("Events");
        refEventsMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAuth = FirebaseAuth.getInstance();
                String myEmail = mAuth.getCurrentUser().getEmail();

                events = new ArrayList<Event>();

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

                listViewMemberEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(MemberEventList.this, CurrentEvent.class);
                        intent.putExtra("name", events.get(i).getNameEvent());
                        intent.putExtra("list", events.get(i).getRecipes());
                        intent.putExtra("admin", events.get(i).getEventAdmin());


                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
