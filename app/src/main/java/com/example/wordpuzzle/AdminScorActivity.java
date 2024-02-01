package com.example.wordpuzzle;

import static com.example.wordpuzzle.R.id.toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminScorActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    adminadapter adminadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_scor);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Here............");
        recyclerView=(RecyclerView)findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<adminholder> options=
                new FirebaseRecyclerOptions.Builder<adminholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"),adminholder.class)
                        .build();
        adminadapter=new adminadapter(options);
        recyclerView.setAdapter(adminadapter);
    }
    protected void onStart(){
        super.onStart();
        adminadapter.startListening();
    }
    protected void onStop(){
        super.onStop();
        adminadapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuscor,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchingprocess(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchingprocess(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void searchingprocess(String query){

        FirebaseRecyclerOptions<adminholder>options=
                new FirebaseRecyclerOptions.Builder<adminholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users")
                                        .orderByChild("level").startAt(query).endAt(query + "\uf8ff")
                                , adminholder.class)
                        .build();

        adminadapter =new adminadapter(options);
        adminadapter.startListening();
        recyclerView.setAdapter(adminadapter);

    }
}
