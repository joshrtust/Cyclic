package com.example.deliverable1test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.deliverable1test.organizer.OrganizerActivity;
import com.example.deliverable1test.participant.ParticipantActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {//What does this MainActivity class do uniquely?


    FirebaseAuth auth;
    FirebaseUser user;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        email = user.getEmail();

        FirebaseApp.initializeApp(this);

        if(user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else if(email.contains("club")){
            Intent intent = new Intent(getApplicationContext(), OrganizerActivity.class);
            startActivity(intent);
            finish();
        } else{
            Intent intent = new Intent(getApplicationContext(), ParticipantActivity.class);
            startActivity(intent);
            finish();
        }


    }
}