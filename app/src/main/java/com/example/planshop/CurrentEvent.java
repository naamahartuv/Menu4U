package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentEvent extends AppCompatActivity {
    private String eventName, adminEmail;
    private ArrayList<String> recipeList;
    private TextView title;
    private ListView list;
    private DatabaseReference refRecipe;
    private FirebaseAuth mAuth;
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_event);

        final Intent intent = getIntent();
        eventName = intent.getStringExtra("name");
        recipeList = intent.getStringArrayListExtra("list");
        adminEmail = intent.getStringExtra("admin");



        title = findViewById(R.id.textView8);
        title.setText(eventName);


        list = findViewById(R.id.listRecipe);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                CurrentEvent.this,
                android.R.layout.simple_list_item_1,
                recipeList
        );
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                refRecipe = FirebaseDatabase.getInstance().getReference("Recipes");

                refRecipe.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mAuth = FirebaseAuth.getInstance();
//                        final String myEmail = mAuth.getCurrentUser().getEmail();
                        recipes = new ArrayList<Recipe>();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Recipe recipe = ds.getValue(Recipe.class);
                            if (recipe.getRecipeAdmin().equals(adminEmail)) {
                                recipes.add(recipe);
                            }
                        }

                        Intent intent1 = new Intent(CurrentEvent.this, CurrentRecipe.class);

                        for(Recipe rec : recipes){
                            if(recipeList.get(i).equals(rec.getRecipeName())){
                                if(rec.getRecipeAdmin().equals(adminEmail)){
                                    intent1.putExtra("name", rec.getRecipeName());
                                    intent1.putExtra("ing", rec.getIngredients());
                                    intent1.putExtra("dir", rec.getDirections());

                                    startActivity(intent1);
                                }

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




//                intent1.putExtra("name" , )
            }
        });
    }


    public void creatRecipeList() {
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
