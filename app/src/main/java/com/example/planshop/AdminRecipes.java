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

public class AdminRecipes extends AppCompatActivity {

    private DatabaseReference refRecipe;
    private ListView listViewAdminRecipe;
    private FirebaseAuth mAuth;
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        recipes = new ArrayList<Recipe>();
        listViewAdminRecipe = findViewById(R.id.listRecipe);
        showRecipeList();
    }

    public void addRecipe(View view) {
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }

    public void showRecipeList() {
        Log.d("TAG4", "showRecipeList");
        refRecipe = FirebaseDatabase.getInstance().getReference("Recipes");

        refRecipe.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAuth = FirebaseAuth.getInstance();
                String myEmail = mAuth.getCurrentUser().getEmail();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Recipe recipe = ds.getValue(Recipe.class);
                    if (recipe.getRecipeAdmin().equals(myEmail)) {
                        recipes.add(recipe);
                    }
                }

                ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(
                        AdminRecipes.this,
                        android.R.layout.simple_list_item_1,
                        recipes);

                listViewAdminRecipe.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
