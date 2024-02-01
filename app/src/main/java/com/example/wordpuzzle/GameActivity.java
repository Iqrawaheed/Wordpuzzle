package com.example.wordpuzzle;

import static com.example.wordpuzzle.R.id.timerTextView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends Activity implements OnClickListener {

    // UI elements and necessary variables
    TextView tv1, tv2, tv3, sh;
    Button b1, b2;
    EditText et1;
    String n = "", c = "", l = "";
    int score = 0, i = 0, j = 0;
    MediaPlayer p;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30 * 1000; // 30 seconds
    private boolean timerFinished = false;  // Variable to track whether the timer has finished
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initializing UI elements and media player
        p = MediaPlayer.create(this, R.raw.button);

        tv1 = findViewById(R.id.gp_tv1);
        tv2 = findViewById(R.id.gp_tv2);
        tv3 = findViewById(R.id.gp_tv3);
        sh = findViewById(R.id.gp_sh);
        b1 = findViewById(R.id.gp_b1);
        b2 = findViewById(R.id.gp_hint);
        et1 = findViewById(R.id.gp_et1);
        timerTextView = findViewById(R.id.timerTextView);

        // Retrieving data from intent and setting welcome message
        n = "Player"; // Set a default name
        c = "animal"; // Set a default category
        l = "Level 1"; // Set a default level

        // Retrieving data from intent and setting welcome message
        Intent ri = getIntent();
        if (ri != null && ri.getExtras() != null) {
            Bundle data = ri.getExtras();
            if (data.containsKey("n1")) {
                n = data.getString("n1", "player");
            }
            if (data.containsKey("c1")) {
                c = data.getString("c1", "animal");
            }
            if (data.containsKey("l1")) {
                l = data.getString("l1", "Level 1");
            }

            // Log to check if values are retrieved correctly
            Log.d("GameActivity", "Name: " + n + ", Category: " + c + ", Level: " + l);
        } else {
            // Handle the case when intent extras are null
            // You may display an error message or handle it according to your app logic
        }

        tv3.setText("Welcome " + n);
        tv2.setText(c + " " + l);
        startGameLogic();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


//        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeftInMillis = millisUntilFinished;
//                updateCountdownText();
//            }
//
//            @Override
//            public void onFinish() {
//                Log.d("GameActivity", "Timer finished!");
//
//                // Check if the timer is close to finishing (e.g., less than 5 seconds remaining)
//                if (timeLeftInMillis <= 5000) {
//                    // Game finish conditions when the timer is close
//                    handleGameFinish();
//                } else {
//                    // Continue with normal game over conditions
//                    handleGameOver();
//                }
//            }
//
//            private void handleGameOver() {
//                // Add your normal game over logic here
//                // For example, showing a message, stopping further input, etc.
//
//                // For now, let's just show a log message and finish the activity
//                Log.d("GameActivity", "Game Over!");
//
//                Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
//                i.putExtra("sc", score);
//                i.putExtra("n1", n);
//                i.putExtra("c1", c);
//                i.putExtra("l1", l);
//                startActivity(i);
//                finish();
//            }
//
//            private void handleGameFinish() {
//                // Add your game finish logic here
//                // For example, showing a message, stopping further input, etc.
//
//                // For now, let's just show a log message and finish the activity
//                Log.d("GameActivity", "Game Finish!");
//
//                Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
//                i.putExtra("sc", score);
//                i.putExtra("n1", n);
//                i.putExtra("c1", c);
//                i.putExtra("l1", l);
//                startActivity(i);
//                finish();
//            }
//        }.start();
//
//
//    }
//
//    private void updateCountdownText() {
//        int seconds = (int) (timeLeftInMillis / 1000) % 60;
//
//        String timeLeftFormatted = String.format("0:%02d", seconds);
//        timerTextView.setText(timeLeftFormatted);
//    }
//
//
//



        // Set default timer duration
        long defaultTime = 2 * 60 * 1000; // 2 minutes

        // Set timer duration based on the selected level
        if (l.equalsIgnoreCase("Level 1")) {
            defaultTime = 2 * 60 * 1000; // 2 minutes for Level 1
        } else if (l.equalsIgnoreCase("Level 2")) {
            defaultTime = 1 * 60 * 1000; // 1 minute for Level 2
        } else if (l.equalsIgnoreCase("Level 3")) {
            defaultTime = 30 * 1000; // 30 seconds for Level 3
        }

        timeLeftInMillis = defaultTime;

        // ... (rest of your existing code)

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }



                private void updateCountdownText() {
                    int minutes = (int) (timeLeftInMillis / 1000) / 60;
                    int seconds = (int) (timeLeftInMillis / 1000) % 60;

                    String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                    timerTextView.setText(timeLeftFormatted);
                }



            @Override
            public void onFinish() {
                // ... (existing onFinish logic)
                Log.d("GameActivity", "Timer finished!");
//
//                // Check if the timer is close to finishing (e.g., less than 5 seconds remaining)
                if (timeLeftInMillis <= 5000) {
                    // Game finish conditions when the timer is close
                    handleGameFinish();
                } else {
                    // Continue with normal game over conditions
                    handleGameOver();
                }
            }
            private void handleGameOver() {
//                // Add your normal game over logic here
//                // For example, showing a message, stopping further input, etc.
//
//                // For now, let's just show a log message and finish the activity
                Log.d("GameActivity", "Game Over!");

                Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
                i.putExtra("sc", score);
                i.putExtra("n1", n);
                i.putExtra("c1", c);
                i.putExtra("l1", l);
                startActivity(i);
                finish();
            }
            private void handleGameFinish() {
//                // Add your game finish logic here
//                // For example, showing a message, stopping further input, etc.
//
//                // For now, let's just show a log message and finish the activity
                Log.d("GameActivity", "Game Finish!");

                Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
                i.putExtra("sc", score);
                i.putExtra("n1", n);
                i.putExtra("c1", c);
                i.putExtra("l1", l);
                startActivity(i);
                finish();
            }
        }.start();


    }








    // Method to handle game logic for different categories and levels
    private void startGameLogic() {
        {
            try
            {

                if(c.equalsIgnoreCase("animal")==true)
                {
                    if(l.equalsIgnoreCase("level 1")==true)
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            String ans=et1.getText().toString();
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.animal_q1[i]);
                            }
                            if(ans.equalsIgnoreCase(WordsDB.animal_a1[j]))
                            {
                                score=score+1;;
                            }
                        }

                        i++;
                        et1.setText("");
                    }
                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.animal_q2[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.animal_a2[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.animal_q3[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.animal_a3[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }
                }


                else if(c.equalsIgnoreCase("biology"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.biology_q1[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.biology_a1[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.biology_q2[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.biology_a2[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");

                    }
                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.biology_q3[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.biology_a3[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                }

                else if(c.equalsIgnoreCase("country"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.country_q1[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.country_a1[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");

                    }
                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.country_q2[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.country_a2[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");

                    }
                    else   if(l.equalsIgnoreCase("Level 3"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.country_q3[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.country_a3[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }
                }


                else if(c.equalsIgnoreCase("elements"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.element_q1[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.element_a1[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.element_q2[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.element_a2[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.element_q3[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.element_a3[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }
                }


                else if(c.equalsIgnoreCase("sports"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.sport_q1[i]);
                            }

                            String ans=et1.getText().toString();
                            if(WordsDB.sport_a1[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");

                    }
                    else   if(l.equalsIgnoreCase("Level 2"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.sport_q2[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.sport_a2[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.sport_q3[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.sport_a3[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }
                }


                else if(c.equalsIgnoreCase("techno"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.robot_q1[i]);
                            }

                            String ans=et1.getText().toString();
                            if(WordsDB.robot_a1[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.robot_q2[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.robot_a2[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");

                    }
                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        if(j<=14)
                        {
                            if(i==14)
                            {
                                b1.setText("FINISH");
                            }
                            if(i<=14)
                            {
                                tv1.setText(WordsDB.robot_q3[i]);
                            }
                            String ans=et1.getText().toString();
                            if(WordsDB.robot_a3[j].equalsIgnoreCase(ans))
                            {
                                score=score+1;
                            }
                        }

                        i++;
                        et1.setText("");
                    }
                }


            }
            catch(Exception e)
            {

            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_play, menu);
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
    protected void onDestroy() {
        super.onDestroy();
//        @Override
//        protected void onDestroy() {
//            super.onDestroy();

            // Cancel the countdown timer to prevent memory leaks
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.gp_b1) {
            // On button click, start game logic, increment counters, and check for game completion
            p.start();
            startGameLogic();
            j++;

            if (j > 14) {


                // If the game is completed, set the timerFinished variable to true
                timerFinished = true;

                // Call the method to handle game finish or game over
                handleGameCompletion();
                // If the game is completed, proceed to the score activity
                Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
                i.putExtra("sc", score); // Adding score as an extra
                i.putExtra("n1", n);     // Adding n as an extra
                i.putExtra("c1", c);     // Adding c as an extra
                i.putExtra("l1", l);     // Adding l as an extra
                startActivity(i);        // Starting ScoruserActivity
                finish();                // Finishing the current GameActivity

//                Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
//                i.putExtra("sc", score);
//                i.putExtra("n1", n);
//                i.putExtra("c1", c);
//                i.putExtra("l1", l);
//                startActivity(i);
//                finish();
            }
            sh.setText("Starts With: ");
        } else if (id == R.id.gp_hint) {
            try
            {
                if(c.equalsIgnoreCase("animal")==true)
                {
                    if(l.equalsIgnoreCase("level 1")==true)
                    {
                        sh.setText("Starts With : "+(WordsDB.animal_a1[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        sh.setText("Starts With : "+(WordsDB.animal_a2[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        sh.setText("Starts With : "+(WordsDB.animal_a3[j]).charAt(0));
                    }
                }


                else if(c.equalsIgnoreCase("biology"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        sh.setText("Starts With : "+(WordsDB.biology_a1[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        sh.setText("Starts With : "+(WordsDB.biology_a2[j]).charAt(0));

                    }
                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        sh.setText("Starts With : "+(WordsDB.biology_a3[j]).charAt(0));
                    }

                }

                else if(c.equalsIgnoreCase("country"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        sh.setText("Starts With : "+(WordsDB.country_a1[j]).charAt(0));
                    }
                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        sh.setText("Starts With : "+(WordsDB.country_a2[j]).charAt(0));

                    }
                    else   if(l.equalsIgnoreCase("Level 3"))
                    {
                        sh.setText("Starts With : "+(WordsDB.country_a3[j]).charAt(0));
                    }
                }


                else if(c.equalsIgnoreCase("elements"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        sh.setText("Starts With : "+(WordsDB.element_a1[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        sh.setText("Starts With : "+(WordsDB.element_a2[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        sh.setText("Starts With : "+(WordsDB.element_a3[j]).charAt(0));
                    }
                }


                else if(c.equalsIgnoreCase("sports"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        sh.setText("Starts With : "+(WordsDB.sport_a1[j]).charAt(0));

                    }
                    else   if(l.equalsIgnoreCase("Level 2"))
                    {
                        sh.setText("Starts With : "+(WordsDB.sport_a2[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        sh.setText("Starts With : "+(WordsDB.sport_a3[j]).charAt(0));
                    }
                }


                else if(c.equalsIgnoreCase("techno"))
                {
                    if(l.equalsIgnoreCase("Level 1"))
                    {
                        sh.setText("Starts With : "+(WordsDB.robot_a1[j]).charAt(0));
                    }

                    else if(l.equalsIgnoreCase("Level 2"))
                    {
                        sh.setText("Starts With : "+(WordsDB.robot_a2[j]).charAt(0));
                    }
                    else if(l.equalsIgnoreCase("Level 3"))
                    {
                        sh.setText("Starts With : "+(WordsDB.robot_a3[j]).charAt(0));
                    }
                }

            }
            catch (Exception e) {
                // Handle exceptions gracefully
            }
        }
    }
    // Method to handle game completion logic
    private void handleGameCompletion() {
        // Check if both conditions are met: timer finished and game completed
        if (timerFinished && j > 14) {
            // If yes, proceed to the score activity
            Intent i = new Intent(GameActivity.this, ScoruserActivity.class);
            i.putExtra("sc", score);
            i.putExtra("n1", n);
            i.putExtra("c1", c);
            i.putExtra("l1", l);
            startActivity(i);
            finish();
        }
    }

    // Rest of the code remains the same
}








