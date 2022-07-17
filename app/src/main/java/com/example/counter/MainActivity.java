package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler mainHandler = new Handler();
    boolean running = false;


    class NewThread extends Thread {
//  --------------- different thread ---------------
        @Override
        public void run() {
            while (running) {

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        counter++;
                        display.setText(String.valueOf(counter));
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Button startbtn, stopbtn;
    TextView display;

    int counter = 0;


    NewThread counterThread = new NewThread();


//    --------------- this runnig in main thread ! ---------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn = (Button) findViewById(R.id.start);
        stopbtn = (Button) findViewById(R.id.stop);
        display = (TextView) findViewById(R.id.display);

        counter = 0;


        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running =  true;
                counter = 0;

                if(counterThread.isAlive());
                else{
                    counterThread = new NewThread();
                    counterThread.start();
                }
            }
        });

        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
    }


}