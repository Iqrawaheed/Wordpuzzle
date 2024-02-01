package com.example.wordpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSelectActivity extends AppCompatActivity {

    Button b1;
    EditText et1;
    static MediaPlayer p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);
        p = MediaPlayer.create(this, R.raw.button);

        b1 = findViewById(R.id.us_b1);
        et1 = findViewById(R.id.us_et1);

        SharedPreferences sp = getSharedPreferences("users", MODE_PRIVATE);
        String sv = sp.getString("n", "");
        et1.setText(sv);

//        b1.setOnClickListener((View.OnClickListener) this);
        b1.setOnClickListener(this::onClick);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//   b1.setOnClickListener(new View.OnClickListener() {
//        @Override
        public void onClick(View v) {
            // Handle button click event here
            if (v.getId() == R.id.us_b1) {
                String s = et1.getText().toString();
                if (s.equals("") || s.trim().isEmpty()) {
                    Toast.makeText(UserSelectActivity.this, "Please enter your name!!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sp = getSharedPreferences("users", MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("n", s);
                    e.apply();

                    Intent i = new Intent(UserSelectActivity.this, LevelActivity.class);
                    i.putExtra("n1", s);
                    startActivity(i);
                    finish();
                }
            }
        }
}
