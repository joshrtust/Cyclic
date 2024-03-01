package com.example.deliverable1test.organizer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deliverable1test.Login;
import com.example.deliverable1test.R;
import com.google.firebase.auth.FirebaseAuth;

public class OrganizerActivity extends AppCompatActivity {

    Button buttonLogout, createProfile, viewProfile, myEventsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);

        buttonLogout = findViewById(R.id.logout);
        createProfile = findViewById(R.id.createProfile);
        viewProfile = findViewById(R.id.myProfileInformation); // Linking the view profile button
        Button createEventButton = findViewById(R.id.createEvent);
        myEventsButton = findViewById(R.id.myEvents); // Assuming you have a button with ID myEvents

        Toast.makeText(OrganizerActivity.this, "You are logged in as an event organizer",
                Toast.LENGTH_SHORT).show();

        // Logout Button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Create Profile Button
        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizerActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });



        // Create Event Button
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganizerActivity.this, OrganizerCreateEvent.class);
                startActivity(intent);
            }
        });

        // My Events Button

    }
}
