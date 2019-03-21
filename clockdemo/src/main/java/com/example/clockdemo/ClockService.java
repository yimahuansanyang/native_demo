package com.example.clockdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class ClockService extends Service {
    MediaPlayer mediaPlayer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clockAlarm();
        return START_STICKY;
    }

    private void clockAlarm() {
        //創建响铃服务

        try {
//            String sdCard = Environment.getExternalStorageDirectory().getPath();
//            mediaPlayer.setDataSource(sdCard + File.separator + "music.mp3");

//            mediaPlayer.setDataSource(String.valueOf(Uri.parse("android.resource://com.example.clockdemo/raw/music")));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.redrose);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    replay();
                    return false;
                }
            });

            mediaPlayer.prepare();//准备播放

        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();//播放

    }


    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }


    protected void replay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            return;
        }
        clockAlarm();
    }
}
