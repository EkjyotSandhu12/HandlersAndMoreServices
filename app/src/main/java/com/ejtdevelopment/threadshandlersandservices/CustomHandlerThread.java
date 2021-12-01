package com.ejtdevelopment.threadshandlersandservices;

import android.os.Handler;
import android.os.Looper;

public class CustomHandlerThread implements Runnable{


    public Looper threadLooper;
    public Handler handler;

    @Override
    public void run() {

        Looper.prepare();

        threadLooper = Looper.myLooper(); // to get access to current threads looper

        handler = new Handler(); // You can only initiate the handler with looper on the thread

        Looper.loop();

    }
}
