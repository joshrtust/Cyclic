package com.example.deliverable1test.organizer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverable1test.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText editTextSocialMedia, editTextPhoneNumber, editTextContactName;
    private Button buttonSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        // Initialize EditText fields and Button
        editTextSocialMedia = findViewById(R.id.editTextSocialMedia);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextContactName = findViewById(R.id.editTextContactName);
        buttonSaveProfile = findViewById(R.id.buttonSaveProfile);

        // Set a click listener for the "Save Profile" button
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the createProfile method when the button is clicked
                createProfile();
            }
        });
    }

    private void createProfile() {
        // Get the text entered in the EditText fields
        String socialMediaLink = editTextSocialMedia.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String contactName = editTextContactName.getText().toString();

        // Check if the Social Media Link or Phone Number is empty
        if (TextUtils.isEmpty(socialMediaLink) || TextUtils.isEmpty(phoneNumber)) {
            // Display a toast message if either field is empty
            Toast.makeText(this, "Social Media Link and Phone Number are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a unique ID for the new profile
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Profiles");
        String profileId = databaseReference.push().getKey();

        // Create a new Profile object with the entered data
        ClubOwnerProfile profile = new ClubOwnerProfile(socialMediaLink, phoneNumber, contactName);

        // Save the profile data to the Firebase Realtime Database
        databaseReference.child(profileId).setValue(profile).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Display a success message and finish the activity
                Toast.makeText(CreateProfileActivity.this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
                finish(); // Go back to previous activity
            } else {
                // Display an error message if profile data couldn't be saved
                Toast.makeText(CreateProfileActivity.this, "Failed to save profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
