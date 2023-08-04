package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    public boolean isrunning=false;
    public long pauseoffset=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chronometer chronometer=findViewById(R.id.chronometer);
        Button start=findViewById(R.id.startButton);
        Button stop=findViewById(R.id.stopButton);
        Button hold=findViewById(R.id.holdbutton);

        chronometer.setFormat("Timer:%s");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isrunning){
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                    chronometer.start();
                    isrunning=true;
                }
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isrunning){

                    chronometer.stop();
                    pauseoffset=SystemClock.elapsedRealtime()- chronometer.getBase() ;
                    isrunning=false;
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseoffset=0;
                isrunning=false;
                chronometer.stop();
            }
        });

    }
}