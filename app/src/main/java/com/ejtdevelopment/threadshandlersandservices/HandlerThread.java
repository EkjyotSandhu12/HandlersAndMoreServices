package com.ejtdevelopment.threadshandlersandservices;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.renderscript.RenderScript;
import android.util.Log;
import android.widget.Switch;

import androidx.annotation.NonNull;

public class HandlerThread extends android.os.HandlerThread { // handler thread already has looper and message queue as their sub class
    // handler thread is a convenient class for what we did in MainActivity

    //Note: a service doenst gets destroyed when the screen is rotated so handler thread is preferred to be created in Service class

    Handler handler;

    public HandlerThread(/*String name, int priority*/) {
        //name: name can be used for naming the thread that can be used for debugging purpose later
        // priority: how much processing time a thread should get. higher the number, lower the priority
        // high priority gets more attention from the processor

       //super(name, priority);

        super("HandlerThread", Process.THREAD_PRIORITY_BACKGROUND); //android.os.Process
        //Process.setThreadPriority(THREAD_PRIORITY_DEFAULT); To later change the priority
    }

    @Override
    protected void onLooperPrepared() {
        //  super.onLooperPrepared(); you can delete the super call bcz it is empty and doesn't matter
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch(msg.what){
                    case 1:
                        Log.d("Handler Thread", "msg.what: " + msg.what + "\nmsg.arg1: " + msg.arg1
                        + "\nmsg.obj: " + msg.obj);
                }
            }
        };

    }

    public Handler getHandler() {
        return handler;
    }

}
