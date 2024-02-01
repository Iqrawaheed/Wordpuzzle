package com.example.wordpuzzle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDashboard extends AppCompatActivity {
//    private Button buttonstart;
//    public static final int  REQUEST_CODE = 1;
//    private TextView txtlaststep;
//    private TextView txtlasttime;
//    private TextView txtbeststep;
//    private TextView txtbesttime;
//    private MyBase myBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
//        buttonstart=findViewById(R.id.buttonstart);
//        txtlaststep=findViewById(R.id.txtlaststep);
//        txtlasttime=findViewById(R.id.txtlasttime);
//        txtbeststep=findViewById(R.id.txtbeststep);
//        txtbesttime=findViewById(R.id.txtbesttime);
//        myBase=new MyBase(this);
//        loadData();
//        buttonstart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(UserDashboard.this, StartActivity.class);
//                startActivity(intent);
//                Intent intent = new Intent(UserDashboard.this, StartGameActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        });
//    }
//    private void loadData(){
//        txtlaststep.setText(String.valueOf(myBase.getLastStep()));
//        txtbeststep.setText(String.valueOf(myBase.getbeststep()));
//        int lasttime=myBase.getLasttime();
//        int lastsecond=lasttime %60;
//        int lasthour=lasttime/3600;
//        int lastminute=(lasttime-lasthour*3600)/60;
//        txtlasttime.setText(String.format("Time :%02d:%02d:%02d",lasthour,lastminute,lastsecond));
//
//        int besttime=myBase.getBesttime();
//        int bestsecond=besttime %60;
//        int besthour=besttime/3600;
//        int bestminute=(besttime-besthour*3600)/60;
//        txtbesttime.setText(String.format("Time :%02d:%02d:%02d",besthour,bestminute,bestsecond));
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==REQUEST_CODE){
//            txtlaststep.setText(String.valueOf(myBase.getLastStep()));
//            int lasttime=myBase.getLasttime();
//            int lastsecond=lasttime %60;
//            int lasthour=lasttime/3600;
//            int lastminute=(lasttime-lasthour*3600)/60;
//            txtlasttime.setText(String.format("Time :%02d:%02d:%02d",lasthour,lastminute,lastsecond));
//
//            int besttime=myBase.getBesttime();
//            int bestsecond=besttime %60;
//            int besthour=besttime/3600;
//            int bestminute=(besttime-besthour*3600)/60;
//            txtbesttime.setText(String.format("Time :%02d:%02d:%02d",besthour,bestminute,bestsecond));
//
//        }
//    }
    }

    public void gotostart(View view) {

        Intent intent=new Intent(UserDashboard.this, StartActivity.class);
        startActivity(intent);
    }
}