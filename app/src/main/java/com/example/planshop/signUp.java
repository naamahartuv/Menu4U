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
import android.widget.AdapterView.OnItemSelectedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signUp extends AppCompatActivity {

    private FirebaseAuth users;
    private EditText txtname, txtLastName, txtEmail, txtPassword, txtConfirm;
    private Button create;
    private DatabaseReference reff;
    private Member member;

    private Spinner spinner;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // taking FirebaseAuth instance
        users = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        txtname = (EditText) findViewById(R.id.editFirstName);
        txtLastName = (EditText) findViewById(R.id.editLastName);
        txtEmail = (EditText) findViewById(R.id.editEmail);
        txtPassword = (EditText) findViewById(R.id.editPassword);
        txtConfirm = (EditText) findViewById(R.id.editConfirm);
        create = (Button) findViewById(R.id.create);

//yael
//        String[] account = {"Creat", "Participate"};
//        Spinner s = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);

        addListenerOnSpinnerItemSelection();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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
        users.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            reff = FirebaseDatabase.getInstance().getReference().child("Member");
                            member = new Member();
                            member.setPassword(txtPassword.getText().toString().trim());
                            member.setName(txtname.getText().toString().trim());
                            member.setLastName(txtLastName.getText().toString().trim());
                            member.setEmail(txtEmail.getText().toString().trim());

                            reff.child("test").push();
                            reff.push().setValue(member);

                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            Intent log = new Intent(signUp.this, ActivitiesMenu.class);
                            startActivity(log);

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
