package com.ejtdevelopment.threadshandlersandservices;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "looperThread";

    CustomHandlerThread customHandlerThread = new CustomHandlerThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){
        new Thread(customHandlerThread).start();
    }

    public void stop(View view){
        customHandlerThread.threadLooper.quit();
        //customHandlerThread.threadLooper.quitSafely(); // to complete all the remaining task first

    }

    public void taskA(View view){

        customHandlerThread.handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    SystemClock.sleep(1000);
                    Log.d(TAG, "task A run: " + i);
                }
            }
        });
    }

    public void taskB(View view){

        customHandlerThread.handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    SystemClock.sleep(1000);
                    Log.d(TAG, "task B run: " + i);
                }
            }
        });
    }
}