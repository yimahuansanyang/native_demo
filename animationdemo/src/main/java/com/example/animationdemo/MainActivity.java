package com.example.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.list_anim_set);
        hyperspaceJump.setDuration(500);
        hyperspaceJump.setRepeatCount(100);
        iv.startAnimation(hyperspaceJump);
    }
}
