package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipe extends AppCompatActivity {

    private DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }


    public void creatRecpie(View view) {
        ref = FirebaseDatabase.getInstance().getReference().child("Recipes");
        String uid = ref.push().getKey();
        String adminEmail = mAuth.getCurrentUser().getEmail().toString();
    }

}
