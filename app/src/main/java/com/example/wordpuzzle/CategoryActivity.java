package com.example.wordpuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryActivity extends ListActivity {

    String s[] = {"Animal", "Biology", "Country", "Elements", "Sports", "Technology"};
    String n;
    TextView tv1;
    static MediaPlayer p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        p = MediaPlayer.create(this, R.raw.clicksound);

        tv1 = findViewById(R.id.cat_tv1);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        setListAdapter(ad);
        Intent ri = getIntent();
        Bundle data = ri.getExtras();
        n = data.getString("n1");
        tv1.setText("Welcome " + n);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String s1 = s[position];
        p.start();

        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("c1", s1.toLowerCase()); // Sending the selected category
        i.putExtra("l1", getIntent().getStringExtra("l1")); // Passing the level information
        i.putExtra("n1", n);
        startActivity(i);
        finish();
    }
}
