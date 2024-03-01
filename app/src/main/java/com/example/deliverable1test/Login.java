package com.example.deliverable1test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverable1test.admin.AdminActivity;
import com.example.deliverable1test.organizer.OrganizerActivity;
import com.example.deliverable1test.participant.ParticipantActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //changed all "email" to "username" since we ditched FirebaseAuth

    TextInputEditText editTextUsername, editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView textView; // switch to register
    DatabaseReference dbReference;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById((R.id.btn_login));
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String username, password;
                username = String.valueOf(editTextUsername.getText());
                password = String.valueOf(editTextPassword.getText());


                //Checking empty cases
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Login.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.equals("admin") && password.equals("admin")){//admin bypass

                    Intent intent = new Intent(Login.this, AdminActivity.class);
                    startActivity(intent);
                    finish();
                } else {//if not admin.
                    dbReference = FirebaseDatabase.getInstance().getReference("users").child(username);
                    dbReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                Users currentUser;
                                currentUser = snapshot.getValue(Users.class);
                                Intent intent;

                                if(currentUser.getPassword().equals(password))
                                {
                                    if(username.contains("gcc")) //gcc specifies organizers in deliverable 3.
                                    {
                                        intent = new Intent(getApplicationContext(), OrganizerActivity.class);
                                    }
                                    else
                                    {
                                        intent = new Intent(getApplicationContext(), ParticipantActivity.class);
                                    }
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Invalid user", Toast.LENGTH_LONG).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Login.this, "Database error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
}