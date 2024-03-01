/**
 * Activity for handling the event joining form.
 * This activity presents a form where users can enter their name, email, address, and phone number to join an event.
 */
package com.example.deliverable1test.participant;

// Import statements for required Android classes and interfaces
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverable1test.R;

public class JoinFormActivity extends AppCompatActivity {
    // EditText fields for user input
    EditText name, address, email, phone;
    // Button for submitting the form
    Button button;

    @Override
    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_join_form layout
        setContentView(R.layout.activity_join_form);

        // Initialize the views by finding them by their ID
        button = findViewById(R.id.joinform_btn);
        name = findViewById(R.id.joinform_username);
        address = findViewById(R.id.joinform_address);
        email = findViewById(R.id.joinform_email);
        phone = findViewById(R.id.joinform_number);

        // Set an OnClickListener for the button
        button.setOnClickListener(view -> {
            // Check if all the fields are non-empty
            if (name.getText() != null && !name.getText().toString().isEmpty() &&
                    email.getText() != null && !email.getText().toString().isEmpty() &&
                    phone.getText() != null && !phone.getText().toString().isEmpty() &&
                    address.getText() != null && !address.getText().toString().isEmpty()) {

                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // Show a success message
                Toast.makeText(this, "You have successfully joined this event!", Toast.LENGTH_SHORT).show();

                //  go back to the previous activity when the form is successfully submitted
                onBackPressed();
            } else {
                // Show an error message if any field is empty
                Toast.makeText(this, "All input must be not empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
