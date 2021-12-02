package com.ejtdevelopment.threadshandlersandservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "looperThread";
    public static final int TASK_1 = 1;
    CustomThread customThread = new CustomThread();
    // here i have create a thread in which i have created looper and handler to make it work like HandlerThread
    // which is used to mimic the UI thread's purpose of running a thread infinitely, unless quite() is called

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){

        new Thread(customThread).start();
    }

    public void stop(View view){
        customThread.threadLooper.quit();
        //customHandlerThread.threadLooper.quitSafely(); // to complete all the remaining task first
        //if u dont quite the looper/Thread it will become zombie thread
        /*
        A zombie process is a child process/thread that is still waiting for its parent signal
        but the parent process is not running anymore, so the thread is doing nothing
        */
    }

    public void taskA(View view){

        customThread.handler.post(new Runnable() {
           /*
            creating anonymous threads or inner class threads cause memory leaks
            because they have implicit (automatic) reference to the main Activity methods
            and as long as we have reference to the Main Activity, the MainActivity thread will not die (cant be garbage collected)
            even after completion of the task in main activity, as the New thread created is still running
            solution : to create new java file thread or static inner class thread
            */

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
        Message msg = Message.obtain();
        msg.what = TASK_1;
        customThread.handler.sendMessage(msg); // another way to send a task to handler

    }

    static class CustomThread implements Runnable{

       /*
       A static class simple doesn't have reference to the outer class variables and methods (only static variables and method)
       */


        public Looper threadLooper;
        public Handler handler;

        @Override
        public void run() {

            Looper.prepare();

            threadLooper = Looper.myLooper(); // to get access to current threads looper

            // handler = new Handler();
            handler = new CustomHandler(); // created separate class for handler (extending handler)
            // note: here our customHandler also is capable of executing TASK A and TASK B (check above)
            // it will also execute in message queue format

            Looper.loop() ;
        }
    }

     static class CustomHandler extends Handler { // u can directly create "new handler()" if u only wanna run some work directly
                                                // from a "new thread" or static inner class thread
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case TASK_1:
                    Log.d(TAG, "handleMessage: TASK 1 executed");
            }
        }
    }
}