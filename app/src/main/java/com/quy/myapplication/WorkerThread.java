package com.quy.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

public class WorkerThread extends Thread {
    private static final String TAG = "WorkerThread";
    public Handler handler;
    public Handler handlerMainThread;

    public WorkerThread(Handler handlerMainThread) {
        super(TAG);
        this.handlerMainThread = handlerMainThread;
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.obj.toString().equalsIgnoreCase("TASK A")){
                    Message message = Message.obtain();
                    message.obj = msg.obj + " from A";
                    handlerMainThread.sendMessage(message);
                }else{
                    Message message = Message.obtain();
                    message.obj = msg.obj + " from B";
                    handlerMainThread.sendMessage(message);
                }
            }
        };
        Looper.loop();
        Log.i(TAG,"End run()");
    }
}
