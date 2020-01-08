package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipe extends AppCompatActivity {

    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private EditText recipeName, ingredients,directions ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        recipeName = findViewById(R.id.editText);
        ingredients = findViewById(R.id.editText2);
        directions = findViewById(R.id.editText3);
        mAuth = FirebaseAuth.getInstance();



    }


    public void creatRecpie(View view) {
        ref = FirebaseDatabase.getInstance().getReference().child("Recipes");
        String uid = ref.push().getKey();
        String adminEmail = mAuth.getCurrentUser().getEmail().toString();

        Recipe recipe = new Recipe(recipeName.getText().toString(), ingredients.getText().toString(),
                directions.getText().toString(), adminEmail);

        ref.child(uid).setValue(recipe).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(AddRecipe.this, AdminRecipes.class);
                Toast.makeText(getApplicationContext(), "The recipe created successfuly", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }


}
