/**
 * Activity for participants to view and search through a list of events.
 */
package com.example.deliverable1test.participant;

// Import statements for Android and Firebase components
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deliverable1test.Login;
import com.example.deliverable1test.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParticipantActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    // Adapter for the RecyclerView
    participantAdapter adapter;
    // Button for logging out
    Button button;
    // RecyclerView to display events
    RecyclerView recyclerView;
    // SearchView for filtering events
    SearchView searchView;

    @Override
    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_participant layout
        setContentView(R.layout.activity_participant);

        // Initialize the views
        button = findViewById(R.id.logout);
        searchView = findViewById(R.id.customSearchView);
        recyclerView = findViewById(R.id.user_recyclerview);
        // Inform the user that they are logged in as a user
        Toast.makeText(ParticipantActivity.this, "You are logged in as a user", Toast.LENGTH_SHORT).show();
        // Fetch and display all events
        fetchallevents();

        // Set OnClickListener for the logout button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out from Firebase Auth
                FirebaseAuth.getInstance().signOut();
                // Redirect to the Login activity
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                // Finish the current activity
                finish();
            }
        });
    }

    /**
     * Fetches all events from Firebase and populates the RecyclerView.
     */
    private void fetchallevents() {
        // Reference to the Firebase database where events are stored
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("ClubOwnerEvent");
        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UserEvent> eventList = new ArrayList<>();

                // Iterate through each child (event) in the database snapshot
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    // Extract event details
                    String distance = eventSnapshot.child("distance").getValue(String.class);
                    String eventDate = eventSnapshot.child("eventDate").getValue(String.class);
                    String eventType = eventSnapshot.child("eventType").getValue(String.class);
                    String eventname = eventSnapshot.child("eventname").getValue(String.class);
                    String clubname = eventSnapshot.child("clubname").getValue(String.class);
                    String participants = eventSnapshot.child("participants").getValue(String.class);
                    String route = eventSnapshot.child("route").getValue(String.class);

                    // Check for null values and add event to the list
                    if (distance != null && eventDate != null && eventType != null && eventname != null && clubname != null && participants != null && route != null) {
                        UserEvent event = new UserEvent(distance, eventDate, eventType, participants, route, clubname, eventname);
                        eventList.add(event);
                    } else {
                        // Log any events with missing details
                        Log.e("UserCreateEvent", "Event Name is null for ID: " + eventSnapshot.getKey());
                    }
                }

                // Populate the RecyclerView with events
                if (!eventList.isEmpty()) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(ParticipantActivity.this));
                    adapter = new participantAdapter(ParticipantActivity.this, eventList, eventList);
                    recyclerView.setAdapter(adapter);
                    // Set the SearchView listener for filtering
                    searchView.setOnQueryTextListener(ParticipantActivity.this);
                } else {
                    // Inform if no events are found in the database
                    Toast.makeText(ParticipantActivity.this, "No event names were found in the database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Log any errors encountered when fetching data
                Log.e("UserCreateEvent", "Database error: " + error.getMessage());
            }
        });
    }

    @Override
    /**
     * Called when the query text is changed.
     *
     * @param newText The new content of the query text field.
     * @return true if the action was handled by the listener.
     */
    public boolean onQueryTextChange(String newText) {
        // Filter the adapter's data based on the new text
        adapter.getFilter().filter(newText);
        return true;
    }

    @Override
    /**
     * Called when the user submits the query.
     *
     * @param query The query text that is to be submitted.
     * @return true if the action was handled by the listener.
     */
    public boolean onQueryTextSubmit(String query) {
        return true;
    }
}
