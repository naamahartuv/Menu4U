package com.example.planshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminEventList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_list);
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEvent.class);
        startActivity(intent);
    }
}
