package com.ejtdevelopment.threadshandlersandservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class HandlerThreadActivity extends AppCompatActivity {

    HandlerThread handlerThread = new HandlerThread();
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        handlerThread.start();
    }

    public void start(View view){
        handler = handlerThread.getHandler();
        handler.post(new thread());

        Message msg = Message.obtain();
        msg.what = 1;
        msg.arg1 = 23;
        msg.obj ="any object";
        handler.sendMessage(msg);

        //handlerThread.getHandler().postAtTime(runnable1, token, SystemClock.uptimeMillis());
        //handlerThread.getHandler().postAtFrontOfQueue(new ExampleRunnable2());

    }

    public void stop(View view){
        handlerThread.quit();
    }

    static class thread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d("cool", "Handler Thread run: " + i);
                SystemClock.sleep(1000);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
        //handlerThread.getLooper().quit();
    }
}