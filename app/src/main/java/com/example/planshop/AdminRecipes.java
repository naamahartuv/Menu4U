package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class AdminRecipes extends AppCompatActivity {

    private DatabaseReference refRecipe;
    private ListView listViewAdminRecipe;
    private FirebaseAuth mAuth;
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);


        listViewAdminRecipe = findViewById(R.id.listRecipe);
        showRecipeList();
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
                Intent intent = new Intent(this, ActivitiesMenu.class);
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
                final String myEmail = mAuth.getCurrentUser().getEmail();
                recipes = new ArrayList<Recipe>();

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

                listViewAdminRecipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(AdminRecipes.this, CurrentRecipe.class);

                        intent.putExtra("name", recipes.get(i).getRecipeName());
                        intent.putExtra("ing", recipes.get(i).getIngredients());
                        intent.putExtra("dir", recipes.get(i).getDirections());

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
