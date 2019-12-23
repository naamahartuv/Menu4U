package com.example.planshop;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {

        private String firstName;
        private String lastName;
        private String Email;
        private Boolean isAdmin;

        public User(){

        }
        public User(String firstName,String lastName,String email,Boolean isAdmin){
            this.firstName = firstName;
            this.lastName = lastName;
            this.Email = email;
            this.isAdmin = isAdmin;
        }

        public User(String email){
            this.Email = email;
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String name) {
            this.firstName = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String toString(){
            return "" + this.Email;
    }

}


