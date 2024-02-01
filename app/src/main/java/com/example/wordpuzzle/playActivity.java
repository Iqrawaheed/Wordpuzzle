package com.example.wordpuzzle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class playActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1, b2, b3;
    private DatabaseReference databaseReference;
    private MediaPlayer p, p1;

    RelativeLayout screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        b1 = findViewById(R.id.sp_b1);
        b2 = findViewById(R.id.sp_b2);
        b3 = findViewById(R.id.sp_b3);
        screen= (RelativeLayout)findViewById(R.id.sp_screen);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("JumbleWords");

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

        if (p == null) {
            p = MediaPlayer.create(this, R.raw.allinfashion);
            p.start();
            p.setLooping(true);
        }
        p1 = MediaPlayer.create(this, R.raw.button);

    }


//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.sp_b1) {
//            // Handle button b1 click
//            p1.start(); // Play button sound
//            // Perform other actions
//        } else if (v.getId() == R.id.sp_b2) {
//            // Handle button b2 click
//            p1.start(); // Play button sound
//            // Perform other actions
//        } else if (v.getId() == R.id.sp_b3) {
//            // Handle button b3 click
//            p1.start(); // Play button sound
//            // Perform other actions
//        }
//    }
    protected void onRestart() {
        super.onRestart();

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    boolean mu = dataSnapshot.child("music").getValue(Boolean.class);
                    String bg = dataSnapshot.child("bg").getValue(String.class);

                    if (mu) {
                        p.start();
                        p.setLooping(true);
                    } else {
                        p.start();
                        p.pause();
                    }

                    if ("Background 1".equalsIgnoreCase(bg)) {
                        screen.setBackgroundResource(R.drawable.bg1);
                    } else if ("Background 2".equalsIgnoreCase(bg)) {
                        screen.setBackgroundResource(R.drawable.bg2);
                    } else if ("Background 3".equalsIgnoreCase(bg)) {
                        screen.setBackgroundResource(R.drawable.bg3);
                    } else if ("Background 4".equalsIgnoreCase(bg)) {
                        screen.setBackgroundResource(R.drawable.bg4);
                    } else if ("Background 5".equalsIgnoreCase(bg)) {
                        screen.setBackgroundResource(R.drawable.bg5);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error in reading from the database
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_page, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sp_ch1) {
            // Load 'About App' data from Firebase and display in an AlertDialog
            loadAboutAppDataAndShowDialog();
        } else if (id == R.id.sp_ch2) {
            // Start the SettingPage activity
            startActivity(new Intent(this,UserSelectActivity.class));
        } else if (id == R.id.sp_ch3) {
            // Show exit confirmation dialog
            showExitConfirmDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadAboutAppDataAndShowDialog() {
        // Assuming you have a 'about_app_data' node in Firebase containing the relevant data
        DatabaseReference aboutAppRef = FirebaseDatabase.getInstance().getReference().child("about_app_data");
        aboutAppRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String aboutAppMessage = dataSnapshot.child("message").getValue(String.class);
                    displayAboutAppDialog(aboutAppMessage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error in reading from the database
            }
        });
    }

    private void displayAboutAppDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About My App");
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showExitConfirmDialog() {
        // Implement your exit confirmation dialog logic here
    }
    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub

    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sp_b1) {
            p1.start();
            startActivity(new Intent(this, UserSelectActivity.class));
        } else if (id == R.id.sp_b2) {
            p1.start();
            showInstructionsDialog();
        } else if (id == R.id.sp_b3) {
            p1.start();
            DatabaseReference highscoresRef = FirebaseDatabase.getInstance().getReference().child("highscores");
            highscoresRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    StringBuilder records = new StringBuilder("NAME\tSCORE\t\tCATG.\t\t\tLEVEL\n-----------------------------------------------------");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String n = snapshot.child("name").getValue(String.class);
                        String s = snapshot.child("score").getValue(String.class);
                        String c = snapshot.child("category").getValue(String.class);
                        String l = snapshot.child("level").getValue(String.class);
                        records.append("\n").append(n).append("\t\t").append(s).append("\t\t\t\t").append(c).append("\t\t").append(l);
                    }
                    showData(records.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error in reading from the database
                }
            });
        }
    }

    private void showInstructionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INSTRUCTIONS");
        builder.setIcon(R.drawable.bkgrnd);
        builder.setMessage("Select Any Category and level\nOf your choice\nAnd guess the answer\nClick on HINT button if required.");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showData(String data) {
        // Implement logic to display data
    }

    private void showDataFromFirebase() {
        DatabaseReference highscoresRef = FirebaseDatabase.getInstance().getReference().child("highscores");
        highscoresRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder records = new StringBuilder("NAME\tSCORE\t\tCATG.\t\t\tLEVEL\n-----------------------------------------------------");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String n = snapshot.child("name").getValue(String.class);
                    String s = snapshot.child("score").getValue(String.class);
                    String c = snapshot.child("category").getValue(String.class);
                    String l = snapshot.child("level").getValue(String.class);
                    records.append("\n").append(n).append("\t\t").append(s).append("\t\t\t\t").append(c).append("\t\t").append(l);
                }
                displayHighScores(records.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error in reading from the database
            }
        });
    }

    private void displayHighScores(String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("High Scores");
        builder.setIcon(R.drawable.bkgrnd);
        builder.setMessage(data);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    void logExitConfirmationEvent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT APP");
        builder.setMessage("Are You Sure To Exit?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logExitConfirmationEvent();
                finish();
            }
        });
        builder.setNegativeButton("NO", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
