package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddEvent extends AppCompatActivity implements PartDialog.PartDialogListener {

    private ArrayList<String> participants;
    private EventRecipes recipes; // to change to adminRecipies
    private ListView listViewPart;
    private ImageButton addButton;
    private DatabaseReference ref;
    private DatabaseReference reff;
    private FirebaseAuth mAuth;
    private EditText txtEventName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        listViewPart = findViewById(R.id.partListView);
        addButton = findViewById(R.id.imageButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        participants = new ArrayList<String>();

        txtEventName = (EditText) findViewById(R.id.eventName);

    }

    public void addEvent(View view) {

        String uid = mAuth.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Events").child(uid);


        String adminEmail = mAuth.getCurrentUser().getEmail().toString();

        Event event = new Event(txtEventName.getText().toString(), participants, recipes, adminEmail);

        ref.setValue(event).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                Log.d("TAG3", "onSuccess");


                String adminUid = mAuth.getCurrentUser().getUid();


                Intent intent = new Intent(AddEvent.this, AdminEventList.class);
                Toast.makeText(getApplicationContext(), "the event created successfuly", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });


    }

    public void openDialog() {
        PartDialog partDialog = new PartDialog();
        partDialog.show(getSupportFragmentManager(), "Part dialog");
    }

    @Override
    public void applyText(final String email) {

        final String temp = email;

        checkEmail(email);

    }


    public void checkEmail(final String email) {

        //check email already exist or not.
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean isExist = !task.getResult().getSignInMethods().isEmpty();

                        if (isExist) {
//                            User user = new User(email);
                            participants.add(email);
//                            participants.getEventUsers().add(user);

                            Log.d("TAG", "Is New User!");
                        } else {

                            Toast.makeText(getApplicationContext(), "User does not exict", Toast.LENGTH_LONG).show();

                            Log.d("TAG", "Is Old User!");
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                AddEvent.this,
                                android.R.layout.simple_list_item_1,
                                participants);

                        listViewPart.setAdapter(adapter);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "Error in fetch email is: " + e);

            }
        });

    }


}
