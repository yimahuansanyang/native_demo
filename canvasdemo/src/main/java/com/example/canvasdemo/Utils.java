package com.example.canvasdemo;

import android.graphics.Color;

public class Utils {
    //获取已知颜色的深色
    public static int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv); // convert to hsv
        // make darker
        hsv[1] = hsv[1] + 0.1f; // more saturation
        hsv[2] = hsv[2] - 0.1f; // less brightness
        int darkerColor = Color.HSVToColor(hsv);
        return darkerColor;
    }

    //获取已知颜色的浅色
    public int getBrighterColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv); // convert to hsv

        hsv[1] = hsv[1] - 0.1f; // less saturation
        hsv[2] = hsv[2] + 0.1f; // more brightness
        int darkerColor = Color.HSVToColor(hsv);
        return darkerColor;
    }
}
