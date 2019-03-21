package com.example.genericitydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starCallableThread();
    }

    private void starCallableThread() {
        CallThread thirdThread = new CallThread();
        FutureTask<Integer> task = new FutureTask<Integer>(thirdThread);

        new Thread(task, "有返回值的线程").start();

        try {
            Integer integer = task.get();
            Log.d("TAG", "starCallableThread, 子线程的返回值=" + integer);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
