package com.example.proxydemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("test", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new MyThread(intent, startId).start();
        Log.i("test", "onStartCommand");
        return Service.START_STICKY;
    }

    class MyThread extends Thread {
        private int startId;
        private Intent intent;

        public MyThread(Intent intent, int startId) {
            this.startId = startId;
            this.intent = intent;
        }

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int i = msg.what;
                MainActivity.progressBar.setProgress(i);
            }
        };

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                handler.sendEmptyMessage(i);
                SystemClock.sleep(200);
                Log.i("test", "   " + i);
            }
            stopSelf(startId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("test", "onDestroy");
    }


}
