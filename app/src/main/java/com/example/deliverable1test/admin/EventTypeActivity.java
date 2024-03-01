package com.example.deliverable1test.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deliverable1test.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventTypeActivity extends AppCompatActivity {

    TextInputLayout age,pace,level,eventname;
    Button createEventButton;
    String valId, valAge,valPace,valLevel,valEventName;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    /**
     * Initializes the activity. This is called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize UI components
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);
        eventname = findViewById(R.id.EventName);
        age = findViewById(R.id.Age);
        pace = findViewById(R.id.Pace);
        level = findViewById(R.id.Level);
        createEventButton = findViewById(R.id.EventButton);


        // Set a click listener on the createEventButton

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If the name value is given allow creation
                if (!TextUtils.isEmpty(eventname.getEditText().getText().toString())) {
                    // Initialize Firebase database

                    rootNode = FirebaseDatabase.getInstance("https://deliverable1test-default-rtdb.firebaseio.com/");
                    reference = rootNode.getReference("Events");

                    // Get values from input fields
                    valEventName = eventname.getEditText().getText().toString();
                    valAge = age.getEditText().getText().toString();
                    valPace = pace.getEditText().getText().toString();
                    valLevel = level.getEditText().getText().toString();

                    valId = reference.push().getKey();


                    // Check if all fields are fille
                    if (valEventName.isEmpty() || valAge.isEmpty() || valPace.isEmpty() || valLevel.isEmpty()) {
                        Toast.makeText(EventTypeActivity.this, "all fields must be filled", Toast.LENGTH_LONG).show();
                        return;
                    }
                    // Validate age input
                    try {
                        int ageValue = Integer.parseInt(age.getEditText().getText().toString());

                    } catch (NumberFormatException e) {
                        age.setError("Invalid age");
                        Toast.makeText(EventTypeActivity.this, "please enter valid age", Toast.LENGTH_LONG).show();
                        return;
                    }
                    // Validate level input
                    try {
                        double levelValue = Double.parseDouble(level.getEditText().getText().toString());

                    } catch (NumberFormatException e) {
                        level.setError("Invalid level");
                        Toast.makeText(EventTypeActivity.this, "please enter valid level", Toast.LENGTH_LONG).show();
                        return;
                    }





                    //Unique id for every Event
                    // Create a new Event object
                    Event event = new Event(valId, valEventName, valAge, valPace, valLevel);
                    reference.child(valId).setValue(event);//reference.setValue only takes objects which is interesting.


                    Toast.makeText(EventTypeActivity.this, "Product successfully added", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                    finish();
                } else {                                                                            //Else show a toast
                    Toast.makeText(EventTypeActivity.this, "Please enter a name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // This is where I send the data of the event creation to firebase.
    public void anEventType(View view){

    }
}