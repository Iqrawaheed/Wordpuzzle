package com.example.wordpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LevelActivity extends ListActivity {
    String c, n;
    TextView tv1;
    static MediaPlayer p;
    String[] s = {"Level 1", "Level 2", "Level 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        p = MediaPlayer.create(this, R.raw.clicksound);

        tv1 = findViewById(R.id.lp_tv1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, s);
        setListAdapter(arrayAdapter);

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            Bundle dataBundle = receivedIntent.getExtras();
            if (dataBundle != null) {
                c = dataBundle.getString("c1");
                n = dataBundle.getString("n1");
                String selectedLevel = dataBundle.getString("l1", "Level 1");
                tv1.setText("Welcome " + n + " ");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p != null) {
            p.release();
            p = null;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String selectedLevel = s[position];

        p.start();

        // Start CategoryActivity and pass the selected level
        Intent i = new Intent(this, CategoryActivity.class);
        i.putExtra("l1", selectedLevel);  // Sending the selected level
        i.putExtra("c1", c);
        i.putExtra("n1", n);
        startActivity(i);
    }
}
