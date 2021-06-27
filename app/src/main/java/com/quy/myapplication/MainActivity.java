package com.quy.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private TextView textView;
    private Handler handlerMainThread = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textView.setText("Value i is " + (String) msg.obj);
        }
    };

    private  WorkerThread workerThread = new WorkerThread(handlerMainThread);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        textView = findViewById(R.id.textView);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });



        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });


    }

    public void start(){
        workerThread.start();
    }


    public void add(){
        Message message = Message.obtain();
        message.obj = "TASK A";
        workerThread.handler.sendMessage(message);

//        workerThread.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Current thread", Thread.currentThread().getId()+"");
//                Log.i("Current thread name", Thread.currentThread().getName()+"");
//                for (int i = 0; i < 5; i++) {
//                    SystemClock.sleep(1000);
//            }
//        }
//        });
    }

    public void quit(){
        Message message = Message.obtain();
        message.obj = "TASK B";
        workerThread.handler.sendMessage(message);
    }
}