package com.example.clockdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHelper {
    private final Context c;
    private final AlarmManager alarmManager;

    public AlarmHelper(Context context) {
        this.c = context;
        alarmManager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
    }

    //开启闹钟
    public void openAlarm(int id, String title, String content, long time) {
        Intent intent = new Intent();
        intent.putExtra("_id", id);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.setClass(c, CallAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(c, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pi);
    }

    //关闭闹钟
    public void closeAlarm(int id, String title, String content) {
        Intent intent = new Intent();
        intent.putExtra("_id", id);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.setClass(c, CallAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(c, id, intent, 0);
        alarmManager.cancel(pi);
    }
}
