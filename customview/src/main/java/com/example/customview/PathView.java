package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private Paint mPaint;
    private Path path;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(100, 300);
        path.rQuadTo(100, -150, 200, 0);
        path.rQuadTo(100, 150, 200, 0);
        path.rQuadTo(100,-150,200,0);
        path.rQuadTo(100, 150, 200, 0);
        path.lineTo(100, 300);
        canvas.drawPath(path, mPaint);
    }
}
