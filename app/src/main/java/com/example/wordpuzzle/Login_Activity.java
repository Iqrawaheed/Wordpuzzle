package com.example.wordpuzzle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {
    TextInputLayout t1, t2;
    ProgressBar progressBar;
    Button btnsubmit;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        t1 = findViewById(R.id.email);
        t2 = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Login(View view) {
        String email = t1.getEditText().getText().toString();
        String password = t2.getEditText().getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin");

                        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String role = dataSnapshot.child("role").getValue(String.class);

                                    if (role != null && role.equalsIgnoreCase("User")) {

                                        redirectToDashboard1();
                                    } else {

                                        redirectToDashboard2();
                                    }
                                } else {

                                    Log.e("LoginActivity", "User data not found in the database");
                                    Toast.makeText(getApplicationContext(), "User data not found in the database", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                Log.e("LoginActivity", "Database error: " + databaseError.getMessage());
                                Toast.makeText(getApplicationContext(), "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {

                        Log.e("LoginActivity", "User is null");
                        Toast.makeText(getApplicationContext(), "User is null", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Exception exception = task.getException();
                    if (exception != null) {
                        Log.e("LoginActivity", "Login failed: " + exception.getMessage());
                        Toast.makeText(getApplicationContext(), "Login failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void redirectToDashboard1() {
        Intent intent = new Intent(Login_Activity.this, StartActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Login successfully as User", Toast.LENGTH_SHORT).show();
    }

    private void redirectToDashboard2() {
        Intent intent = new Intent(Login_Activity.this,AdminScorActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Login successfully as a admin role", Toast.LENGTH_SHORT).show();
    }
}