package com.example.planshop;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private FirebaseDatabase mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUp(View view){
        Intent sign = new Intent (this, signUp.class);
        startActivity(sign);
    }

    public void logIn(View view){
        txtEmail = findViewById(R.id.editMainEmail);
        txtPassword = findViewById(R.id.editMainPass);

//        mDatabase = FirebaseDatabase.getInstance();
//
//        String mail = txtEmail.getText().toString();
//        final String passw = txtPassword.getText().toString();
//        final DatabaseReference myRef = mDatabase.getReference().child("Member");
//        myRef.orderByChild("email").equalTo(mail).addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot){
//                if(dataSnapshot.exists()){
//                    if()
//                    Toast.makeText(getApplicationContext(),"PRESENT",Toast.LENGTH_LONG).show();
//
//
//
//
//                    //kan ovdim
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

        Intent log = new Intent (this, ActivitiesMenu.class);
        startActivity(log);
    }

}
