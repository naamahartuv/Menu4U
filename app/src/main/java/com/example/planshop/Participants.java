package com.example.planshop;

import java.util.ArrayList;

public class Participants {
    private ArrayList<User> eventUsers;

    public Participants(){

    }


    public ArrayList<User> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(ArrayList<User> eventUsers) {
        this.eventUsers = eventUsers;
    }
}
