package com.example.planshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class signUp extends AppCompatActivity  {

    private FirebaseAuth mAuth;
    private EditText txtFirstName, txtLastName, txtEmail, txtPassword, txtConfirm;
    private ArrayList<Event> events;
    private Button create;
    private DatabaseReference reff;
    private User user;
    private Boolean txtIsAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        txtFirstName = (EditText) findViewById(R.id.editFirstName);
        txtLastName = (EditText) findViewById(R.id.editLastName);
        txtEmail = (EditText) findViewById(R.id.editEmail);
        txtPassword = (EditText) findViewById(R.id.editPassword);
        txtConfirm = (EditText) findViewById(R.id.editConfirm);



        create = (Button) findViewById(R.id.create);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.account_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItem().toString().equals("Participate")){
                    txtIsAdmin=false;
                }
                else if(spinner.getSelectedItem().toString().equals("Create")){
                    txtIsAdmin=true;
                }
                registerNewUser();
            }
        });
    }


    private void registerNewUser() {
        // Take the value of two edit texts in Strings
        String email, password;
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();



        // Validations for input email and password
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


        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String uid = mAuth.getUid();
                            reff = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

                            user = new User(txtFirstName.toString(), txtLastName.toString(), txtEmail.toString(), txtIsAdmin);

                            user.setFirstName(txtFirstName.getText().toString().trim());
                            user.setLastName(txtLastName.getText().toString().trim());
                            user.setEmail(txtEmail.getText().toString().trim());


                            reff.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Navigate to corresponding Activity
                                    // Depends is user is admin or not

                                    if (user.getAdmin()) {
                                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                        Intent log = new Intent(signUp.this, ActivitiesMenu.class);
                                        startActivity(log);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                        Intent log = new Intent(signUp.this, MemberEventList.class);
                                        startActivity(log);

                                    }
                                }
                            });

                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!"
                                            + " Please try again",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });


    }


}
