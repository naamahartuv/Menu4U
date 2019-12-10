package com.example.planshop;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signUp extends AppCompatActivity {

    private FirebaseAuth users;
    EditText txtname, txtLastName, txtEmail, txtPassword,txtConfirm;
    Button create;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        users = FirebaseAuth.getInstance();

        txtname = (EditText)findViewById(R.id.editFirstName);
        txtLastName = (EditText)findViewById(R.id.editLastName);
        txtEmail = (EditText)findViewById(R.id.editEmail);
        txtPassword = (EditText)findViewById(R.id.editPassword);
        txtConfirm = (EditText)findViewById(R.id.editConfirm);

        create = (Button) findViewById(R.id.create);
        member = new Member();


        reff = FirebaseDatabase.getInstance().getReference().child("Member");

        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                member.setPassword(txtPassword.getText().toString().trim());
                member.setName(txtname.getText().toString().trim());
                member.setLastName(txtLastName.getText().toString().trim());
                member.setEmail(txtEmail.getText().toString().trim());

                reff.child("test").push();
                reff.push().setValue(member);
                Toast.makeText(signUp.this, "Data inserted sucessfully", Toast.LENGTH_LONG).show();

            }
        });



//        FirebaseAuth.setAndroidContext(this);
//        users = new FirebaseAuth("https://plan-shop.firebaseio.com/");

    }
}
