package com.example.wordpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class StartActivity extends Activity implements android.view.View.OnClickListener,android.content.DialogInterface.OnClickListener {

    ProgressDialog pd;
    Button b1;
    MediaPlayer p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        pd= new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading...");
        pd.setIndeterminate(true);
        pd.setCancelable(true);
        pd.show();

        p=MediaPlayer.create(this, R.raw.button);

        Thread t = new Thread(){
            public void run(){
                try{
                    Thread.sleep(1000);
                    pd.dismiss();
                }catch(Exception e)
                {

                }
            }
        };
        t.start();

        final Animation animation= new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        b1=(Button)findViewById(R.id.la_b1);
        b1.startAnimation(animation);
        b1.setOnClickListener((View.OnClickListener) this);
        buttonEffect(b1);

    }
	/*
	Thread th= new Thread(){
		public void run()

		{
		try{
			sleep(1500);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			onPause();
			startActivity(new Intent(this,StartPage.class));
		}
		}
	};
	th.start();
}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	*/

    public static void buttonEffect(View b1)
    {
        b1.setOnTouchListener(new android.view.View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP );
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.la_b1) {
            // Handle the button click event here
            // This is where you should start your StartPage activity
            p.start();
            Intent i = new Intent(StartActivity.this,playActivity.class);
            startActivity(i);
        }
//        p.start();
//        Intent i = new Intent(MainActivity.this,StartPage.class);
//        startActivity(i);
        // finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater mi =getMenuInflater();
        mi.inflate(R.menu.start_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.la_ch1) {
            //About App
            AlertDialog.Builder bd = new AlertDialog.Builder(this);
            bd.setTitle("About My App");
            bd.setMessage("Developed By: IQRA MALIK / MEHWISH ASIF/ GHAZALI WARSI\nJumble Word Game\nMinimum requirement\nAndroid version 4.2.2\nJelly Bean");
            bd.setPositiveButton("OK", null);
            AlertDialog d= bd.create();
            d.show();
        }

        else if(id==R.id.la_ch2)
        { // ACTION BAR ICON TITLE
            showExitConfirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void showExitConfirmDialog()
    {
        AlertDialog.Builder b= new AlertDialog.Builder(this);
        b.setTitle("EXIT APP");
        b.setMessage("Are You Sure To Exit?");
        b.setIcon(android.R.drawable.ic_delete);
        b.setPositiveButton("YES", new android.content.DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        b.setNegativeButton("NO", null);
        AlertDialog d= b.create();
        d.show();
    }


    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub

    }

}
