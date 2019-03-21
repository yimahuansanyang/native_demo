package com.example.genericitydemo;

import android.util.Log;

import java.util.concurrent.Callable;

public class CallThread implements Callable<Integer> {
    private int ticket = 20;

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            if (this.ticket > 0) {
                Log.e("TAG", Thread.currentThread().getName() + ", 卖票：ticket=" + ticket--);
            }
        }
        return ticket;
    }
}
