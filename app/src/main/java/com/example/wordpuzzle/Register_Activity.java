package com.example.wordpuzzle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity {


    EditText tt1, tt2, tt3, tt4;

    Button btnreg;
    FirebaseAuth mAuth;
    RadioButton g1, g2;
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tt1 = (EditText) findViewById(R.id.name);
        tt2 = (EditText) findViewById(R.id.lastname);
        tt3 = (EditText) findViewById(R.id.email);
        tt4 = (EditText) findViewById(R.id.password);
        g1 = (RadioButton) findViewById(R.id.g1);
        g2 = (RadioButton) findViewById(R.id.g2);
        btnreg = (Button) findViewById(R.id.btnreg);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Registration(View view) {
        String firstname = tt1.getText().toString();
        String lastname = tt2.getText().toString();
        String email = tt3.getText().toString();
        String password = tt4.getText().toString();
        if (g1.isChecked()) {
            gender = "Male";
        }
        if (g2.isChecked()) {
            gender = "Female";
        }
        if (firstname.isEmpty() == false && lastname.isEmpty() == false && email.isEmpty() == false && password.length() >= 8) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String defaultRole="User";
                        Admin admin = new Admin(firstname, lastname, email, gender,defaultRole);
                        FirebaseDatabase.getInstance().getReference("Admin")
                                .child(FirebaseAuth.getInstance().getCurrentUser()
                                        .getUid()).setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        tt1.setText("");
                                        tt2.setText("");
                                        tt3.setText("");
                                        tt4.setText("");
                                        Toast.makeText(Register_Activity.this, "Successfully Registerd", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                                    }
                                });

                    } else {
                        Toast.makeText(Register_Activity.this, "Not Registerd......", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "All Field are mandatory", Toast.LENGTH_SHORT).show();
        }

    }
    public void gotologin(View view)
    {
        Intent intent=new Intent(Register_Activity.this,Login_Activity.class);
        startActivity(intent);
    }
}