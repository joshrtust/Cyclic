package com.example.deliverable1test.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deliverable1test.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Activity for creating new club owner events.
 * Allows the organizer to input event details and store them in Firebase.
 */

public class OrganizerCreateEvent extends AppCompatActivity {

    // UI elements declaration
    private Spinner eventTypeSpinner;
    private EditText eventDateEditText, participantsEditText, routeEditText, distanceEditText,clubname,eventname;
    private Button createEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_create_event); // Setting the layout for the activity

        // Initializing UI elements
        eventTypeSpinner = findViewById(R.id.spinnerEventType);
        eventDateEditText = findViewById(R.id.editTextDay);
        participantsEditText = findViewById(R.id.editTextParticipants);
        routeEditText = findViewById(R.id.editTextRoute);
        distanceEditText = findViewById(R.id.editTextDistance);
        createEventButton = findViewById(R.id.buttonCreateEvent);
        clubname = findViewById(R.id.editTextclubname);
        eventname = findViewById(R.id.editTexteventname);

        // Method call to populate the Spinner with events from Firebase
        fetchExistingEventsFromFirebase();

        // Set OnClickListener for the create event button
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input fields before creating the event
                if (!validateInput()) {
                    return; // Stop further processing if validation fails
                }

                // Prepare event data to store in Firebase
                Map<String, Object> event = new HashMap<>();
                //add two more attributes event name and club name here
                event.put("eventname", eventname.getText().toString().trim());
                event.put("clubname", clubname.getText().toString().trim());
                event.put("eventType", eventTypeSpinner.getSelectedItem().toString());
                event.put("eventDate", eventDateEditText.getText().toString().trim());
                event.put("participants", participantsEditText.getText().toString().trim());
                event.put("route", routeEditText.getText().toString().trim());
                event.put("distance", distanceEditText.getText().toString().trim());

                // Firebase reference to the ClubOwnerEvent node
                DatabaseReference clubOwnerEventRef = FirebaseDatabase.getInstance()
                        .getReference("ClubOwnerEvent");

                // Push the event data to Firebase
                clubOwnerEventRef.push().setValue(event)
                        .addOnSuccessListener(aVoid -> {
                            // Notify the user of successful event creation
                            Toast.makeText(OrganizerCreateEvent.this, "Club Owner Event created successfully", Toast.LENGTH_SHORT).show();

                            // Navigate back to OrganizerActivity
                            Intent intent = new Intent(OrganizerCreateEvent.this, OrganizerActivity.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            // Notify the user in case of failure
                            Toast.makeText(OrganizerCreateEvent.this, "Failed to create club owner event", Toast.LENGTH_SHORT).show();
                            Log.e("OrganizerCreateEvent", "Failed to create club owner event", e);
                        });
            }
        });
    }

    // Method to validate input fields
    private boolean validateInput() {
        // Retrieve input values
        String eventDate = eventDateEditText.getText().toString().trim();
        String participantsText = participantsEditText.getText().toString().trim();
        String route = routeEditText.getText().toString().trim();
        String distanceText = distanceEditText.getText().toString().trim();
        //validating two more attributes event name and club name here

        String eventnaam = eventname.getText().toString().trim();
        String club = clubname.getText().toString().trim();

        // Check for empty fields
        if (eventDate.isEmpty() || participantsText.isEmpty() || route.isEmpty() || distanceText.isEmpty()|| eventnaam.isEmpty()|| club.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false; // Return false if any field is empty
        }

        // Validate numeric input for participants and distance
        try {
            int participants = Integer.parseInt(participantsText);
            double distance = Double.parseDouble(distanceText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Participants must be a whole number and Distance must be a number", Toast.LENGTH_SHORT).show();
            return false; // Return false if parsing fails
        }

        return true; // Return true if all validations pass
    }

    // Method to fetch existing events from Firebase and populate the Spinner
    private void fetchExistingEventsFromFirebase() {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance()
                .getReference("Events");

        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> eventList = new ArrayList<>();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    String eventName = eventSnapshot.child("eventname").getValue(String.class);
                    if (eventName != null) {
                        eventList.add(eventName); // Add event name to the list
                    } else {
                        Log.e("OrganizerCreateEvent", "Event Name is null for ID: " + eventSnapshot.getKey());
                    }
                }

                // Populate Spinner with event names
                if (!eventList.isEmpty()) {
                    ArrayAdapter<String> eventAdapter = new ArrayAdapter<>(
                            OrganizerCreateEvent.this,
                            android.R.layout.simple_spinner_item,
                            eventList
                    );
                    eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    eventTypeSpinner.setAdapter(eventAdapter);
                } else {
                    Log.e("FirebaseData", "No event names were found in the database.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Log error if data retrieval fails
                Log.e("OrganizerCreateEvent", "Database error: " + error.getMessage());
            }
        });
    }


}

