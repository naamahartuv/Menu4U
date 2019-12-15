package com.example.planshop;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUp(View view) {
        Intent sign = new Intent(this, signUp.class);
        startActivity(sign);
    }

    public void logIn(View view) {
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        txtEmail = findViewById(R.id.editMainEmail);
        txtPassword = findViewById(R.id.editMainPass);

        final String email, password;
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    if(email.equals("yaelhava2@gmail.com") ||
                                            email.equals("naama.hartuv@gmail.com") ||
                                            email.equals("michalkin9@gmail.com")){
                                        Toast.makeText(getApplicationContext(),
                                                "Hello ADMIN",
                                                Toast.LENGTH_LONG)
                                                .show();
                                        Intent intent = new Intent(MainActivity.this, GeneralRecipes.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),
                                                "Login successful!",
                                                Toast.LENGTH_LONG)
                                                .show();

                                        Intent intent = new Intent(MainActivity.this, ActivitiesMenu.class);
                                        startActivity(intent);
                                    }
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                }
                            }
                        });


    }
}

