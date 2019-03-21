package com.example.proxydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    public static ProgressBar progressBar;
    private static int PROGRESS = 0;
    private Intent intent;
    private ProgressBar pb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        intent = new Intent(this, MyServices.class);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgress.setProgress(mProgressStatus);
        }
    };

    /**
     * TODO: 方式一：显示默认UI上面的进度条
     */
    public void useProgressByMainThread(View view) {
        Toast.makeText(getApplicationContext(), "useProgressByMainThread", Toast.LENGTH_LONG).show();
        mProgressStatus = 0;
        new Thread(new Runnable() {
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            public void run() {
                String tag = MainActivity.class.getSimpleName();
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();
                    mHandler.sendEmptyMessage(0);
                    Log.d(tag, (System.currentTimeMillis()) / 1000 + "" + mProgressStatus);
                }
            }
        }).start();
    }

    public int doWork() {
        try {
            PROGRESS += 1;
            Thread.currentThread();
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return PROGRESS;
    }

    /**
     * TODO: 方式二：在Service控制进度条，还是回到MainActivity进行显示
     */
    public void useProgressByService(View view) {
        Toast.makeText(getApplicationContext(), "useProgressByService", Toast.LENGTH_LONG).show();
        mProgressStatus = 0;
        new Thread(new Runnable() {
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startService(intent);
    }

    /**
     * TODO: 方式三：动态创建进度条     * https://blog.csdn.net/chdjj/article/details/19825145
     */
    public void useProgressByDynamic(View view) {
        pb = this.createProgressBar(this);
        pb.setVisibility(View.VISIBLE);
    }

    public ProgressBar createProgressBar(Activity a) {
        FrameLayout rootContainer = (FrameLayout) a.findViewById(android.R.id.content);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        ProgressBar pb = new ProgressBar(a);
        pb.setLayoutParams(lp);
        pb.setVisibility(View.GONE);
        rootContainer.addView(pb);
        return pb;
    }


}
