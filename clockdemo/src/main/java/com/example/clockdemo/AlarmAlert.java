package com.example.clockdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

public class AlarmAlert extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = new Intent(AlarmAlert.this, ClockService.class);
        startService(intent);
        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.microphone_sel)
                .setTitle("闹铃")
                .setMessage("循环播放")
                .setPositiveButton("点击取消闹铃", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        Intent intent1 = new Intent(AlarmAlert.this, ClockService.class);
                        stopService(intent1);
                        new AlarmHelper(getApplicationContext()).closeAlarm(32,"ddd","ffff");
                        finish();
//                        System.exit(0);
//                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }).setCancelable(false).show();


    }
}
