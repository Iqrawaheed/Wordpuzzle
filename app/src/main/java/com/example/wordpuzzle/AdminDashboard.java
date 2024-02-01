package com.example.wordpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void gotoadmin(View view) {
        Intent intent=new Intent(AdminDashboard.this,AdminScorActivity.class);
        startActivity(intent);
    }
}