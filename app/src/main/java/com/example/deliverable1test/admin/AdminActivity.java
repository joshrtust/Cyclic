package com.example.deliverable1test.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class AdminActivity extends AppCompatActivity {

    Button logoutButton, addEventTypeButton;
    ListView listViewEvents;
    DatabaseReference dbEvents;
    List<Event> events;


    /**
     * Initializes the activity. This is called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toast.makeText(AdminActivity.this, "You are logged in as admin",
                Toast.LENGTH_SHORT).show();

        logoutButton = findViewById(R.id.logout);
        addEventTypeButton = findViewById(R.id.addEventType);
        dbEvents = FirebaseDatabase.getInstance("https://deliverable1test-default-rtdb.firebaseio.com/").getReference("Events");
        listViewEvents = (ListView) findViewById(R.id.listViewEvents);
        events = new ArrayList<>();

        logoutButton.setOnClickListener(new View.OnClickListener() {                                //Listening for a click on the logout button
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        addEventTypeButton.setOnClickListener(new View.OnClickListener() {                          //Listening for addEventTypeButton to be clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventTypeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listViewEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {       //Listening for a Long click on the listView of events
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Event event = events.get(i);
                showUpdateDeleteDialog(event.getId(), event.getEventname());
                return true;
            }
        });
    }

    /**
     * Called when the activity becomes visible to the user.
     */
    @Override
    protected void onStart() {
        super.onStart();

        dbEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //clearing the previous list
                events.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting product
                    Event event = postSnapshot.getValue(Event.class);
                    //adding product to the list
                    events.add(event);
                }
                //creating adapter
                EventList eventsAdapter = new EventList(AdminActivity.this, events);
                //attaching adapter to the listview
                listViewEvents.setAdapter(eventsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }



    /**
     * Displays a dialog for updating or deleting an event.
     * Sets an OnClickListener for the Update button in the event update dialog.
     * This listener validates the event details entered by the user before updating the event.
     *
     * @param eventId   The ID of the event to be updated or deleted.
     * @param eventName The name of the event to be updated or deleted.
     */
    private void showUpdateDeleteDialog(final String eventId, String eventName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editEventName = (EditText) dialogView.findViewById(R.id.editEventName);
        final EditText editAge = (EditText) dialogView.findViewById(R.id.editAge);
        final EditText editPace = (EditText) dialogView.findViewById(R.id.editPace);
        final EditText editLevel = (EditText) dialogView.findViewById(R.id.editLevel);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        dialogBuilder.setTitle(eventName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieving and trimming input from EditText fields
                String EventName = editEventName.getText().toString().trim();
                String age = editAge.getText().toString().trim();
                String pace = editPace.getText().toString().trim();
                String level = editLevel.getText().toString().trim();

                // Initialize a flag to track validation status
                boolean isValid = true;

                // Validate Event Name - should not be empty
                if (TextUtils.isEmpty(EventName)) {
                    editEventName.setError("Event name is required");
                    isValid = false;
                }

                // Validate Age - should be a valid integer and not empty
                if (!TextUtils.isEmpty(age)) {
                    try {
                        int ageValue = Integer.parseInt(age);

                    } catch (NumberFormatException e) {
                        editAge.setError("Invalid age");
                        isValid = false;
                    }
                } else {
                    editAge.setError("Age is required");
                    isValid = false;
                }
                // Validate Pace - should be  not empty
                if (TextUtils.isEmpty(pace)) {
                    editPace.setError("Pace is required");
                    isValid = false;

                }
                // Validate Level - should be a valid double and not empty
                if (!TextUtils.isEmpty(level)) {
                    try {
                        double levelValue = Double.parseDouble(level);

                    } catch (NumberFormatException e) {
                        editLevel.setError("Invalid level");
                        isValid = false;
                    }
                } else {
                    editLevel.setError("Level is required");
                    isValid = false;
                }

                // Proceed with event update only if all validations are passed
                if (isValid) {
                    updateEvent(eventId, EventName, age, pace, level);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent(eventId);
                b.dismiss();
            }
        });
    }
    /**
     * Updates the details of an existing event in the database.
     *
     * @param eventId    The ID of the event to update.
     * @param eventName  The new name of the event.
     * @param eventAge   The new age range of the event.
     * @param eventPace  The new pace of the event.
     * @param eventLevel The new level of the event.
     */

    private void updateEvent(String eventId, String eventName, String eventAge, String eventPace, String eventLevel) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance("https://deliverable1test-default-rtdb.firebaseio.com/").getReference("Events").child(eventId);

        //updating product
        Event event = new Event(eventId, eventName, eventAge, eventPace, eventLevel);
        dR.setValue(event);

        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
    }

    /**
     * Deletes an event from the database.
     *
     * @param eventId The ID of the event to be deleted.
     */

    private void deleteEvent(String eventId) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance("https://deliverable1test-default-rtdb.firebaseio.com/").getReference("Events").child(eventId);
        //removing product
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Product Deleted", Toast.LENGTH_LONG).show();
    }
}