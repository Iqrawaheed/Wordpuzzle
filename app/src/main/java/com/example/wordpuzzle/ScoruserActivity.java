package com.example.wordpuzzle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ScoruserActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    String n, c, l;
    int sc = 0;
    Button b1, b2;
    FirebaseDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoruser);

        tv1 = (TextView) findViewById(R.id.esp_et1);
        tv2 = (TextView) findViewById(R.id.esp_et2);
        tv3 = (TextView) findViewById(R.id.esp_tv3);
        b1 = (Button) findViewById(R.id.esp_b1);
        b2 = (Button) findViewById(R.id.esp_b2);
        db = FirebaseDatabase.getInstance();


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference("users");

        Intent i = getIntent();
        Bundle data = i.getExtras();
        sc = data.getInt("sc");
        c = data.getString("c1");
        l = data.getString("l1");
        n = data.getString("n1");
        tv1.setText("Congratulations \n" + n);
        tv2.setText("Your Score : " + sc);

// Here, instead of SharedPreferences, you'll use Firebase Database
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int highestScore = 0;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    int userScore = userSnapshot.child("users").getValue(Integer.class);
                    if (userScore > highestScore) {
                        highestScore = userScore;
                    }
                }

                if (sc > highestScore) {
                    highestScore();
                    tv3.setText("HIGHEST SCORE");

                    // Update the high score in Firebase Database
                    DatabaseReference currentUserRef = databaseReference.child("users").push();
                    currentUserRef.child("level").setValue(db);
                }
            }

//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    int chs = dataSnapshot.getValue(Integer.class);
//
//                    if (chs <= sc) {
//                        highestScore();
//                        tv3.setText("HIGHEST SCORE");
//
//                        // Update the high score in Firebase Database
//                        databaseReference.child("users").setValue(sc);
//                    }
//                }
//            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle potential errors here
            }
        });


//        b1.setOnClickListener((OnClickListener) this);
//        b2.setOnClickListener((OnClickListener) this);

        if (b1 != null) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update Firebase Database or perform actions related to the first button click
                    db.getReference("level").setValue(db);

                    Intent i = new Intent(ScoruserActivity.this, CategoryActivity.class);
                    i.putExtra("n1", n);
                    startActivity(i);
                    finish();

                }

            });
        }

        if (b2 != null) {
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform actions related to the second button click or update the database
                    db.getReference("level").setValue(db);

                    finish();

                }
                // ...
            });
        }
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update Firebase Database or perform actions related to the first button click
//                db.getReference("level").setValue(db);
//
//                Intent i = new Intent(ScoruserActivity.this, CategoryActivity.class);
//                i.putExtra("n1", n);
//                startActivity(i);
//                finish();
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform actions related to the second button click or update the database
//                db.getReference("level").setValue(db);
//
//                finish();
//            }
//        });


//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update Firebase Database or perform actions related to the first button click
//                db.getReference("level").setValue(db);
//
//                Intent i = new Intent(ScoruserActivity.this, CategoryActivity.class);
//                i.putExtra("n1", n);
//                startActivity(i);
//                finish();
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform actions related to the second button click or update the database
//                db.getReference("level").setValue(db);
//
//                finish();
//            }
//        });


    }

    void highestScore() {
        // Update shared preferences (if required, though not recommended in Firebase)
        // SharedPreferences sp = getSharedPreferences("jw_score", MODE_PRIVATE);
        // SharedPreferences.Editor e = sp.edit();
        // e.remove("hs");
        // e.putInt("hs", sc);
        // e.apply(); // Using apply() for asynchronous writes, if immediate consistency is not necessary


        // Create a DatabaseReference pointing to the "highscores" node
//        DatabaseReference highScoresRef = db.getReference("level").push();
        DatabaseReference highScoresRef = db.getReference().child("users").push();

// Assuming each score will have a unique key

        // Create a HashMap to store the high score details
        Map<String, Object> highScoreDetails = new HashMap<>();
        highScoreDetails.put("name", n);
        highScoreDetails.put("score", sc);
        highScoreDetails.put("catg", c);
        highScoreDetails.put("level", l);

        // Update the high score details in Firebase Realtime Database
        highScoresRef.setValue(highScoreDetails)
                .addOnSuccessListener(aVoid -> {
                    // Success handling if needed
                })
                .addOnFailureListener(e -> {
                    // Error handling if needed
                });
    }

//    public void gotocat(View view) {
//        Intent intent=new Intent(ScoruserActivity.this,LevelActivity.class);
//        startActivity(intent);
//    }


//    public void onClick(View v) {
//        int id = v.getId();
//
//        if (id == R.id.esp_b1) {
//            // Update Firebase Database or perform actions related to the first button click
//            // Assuming you want to update a certain value or perform an action when b1 is clicked
//            // You can modify this section to perform the necessary database operations
//            // For example, updating a specific value
//            db.getReference("level").setValue(db);
//
//            Intent i = new Intent(ScoruserActivity.this, CategoryActivity.class);
//            i.putExtra("n1", n);
//            startActivity(i);
//            finish(); // Finish the current activity
////        } else if (id == R.id.esp_b2) {
////            // Perform actions related to the second button click or update the database
////            // For example, if this button signifies an exit or completion action
////            // Update the database or perform necessary actions
////
////            // For example, updating a different value when b2 is clicked
////            db.getReference("level").setValue(db);
////
////            finish(); // Finish the current activity
////        }
//        }
//
//
//    }
//
    public void logout(View view) {
        Intent intent = new Intent(ScoruserActivity.this,StartActivity.class);
        startActivity(intent);
     //   getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();

    }
//public void onClick(View v) {
//    // TODO Auto-generated method stub
//
//    int id=v.getId();
//
//    if(id==R.id.esp_b1)
//    {
//        Intent i=new Intent(this,CategoryActivity.class);
//        i.putExtra("n1", n);
//        startActivity(i);
//        finish();
//    }
//
//    else if(id==R.id.esp_b2)
//    {    Intent i=new Intent(this,MainActivity.class);
//        startActivity(i);
//        finish();
//    }
//
//
//}

}



