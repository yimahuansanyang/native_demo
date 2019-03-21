package com.example.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class MyView extends View {
    private String mtext;
    private int msrc;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int resourceId = 0;
        int textId = attrs.getAttributeResourceValue(null, "Text", 0);
        int srcId = attrs.getAttributeResourceValue(null, "Src", 0);
        mtext = context.getResources().getText(textId).toString();
        msrc = srcId;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(20);
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(20);
        paint1.setAntiAlias(true);
        InputStream is = getResources().openRawResource(msrc);
        Bitmap mBitmap = BitmapFactory.decodeStream(is);
        int bh = mBitmap.getHeight();
        int bw = mBitmap.getWidth();
        canvas.drawBitmap(mBitmap, bw/3-20, 0, paint);
        //canvas.drawCircle(40, 90, 15, paint);
        canvas.drawText(mtext, bw / 2+20, bh + 30, paint);
//        canvas.drawCircle(bw / 2, bh / 2 - 20, 40, paint);
//        canvas.drawLine(bw / 2 - 35, bh/2-10, 30, bh/2-30, paint1);
//        canvas.drawLine(bw / 2 + 35, bh/2-10, bw-30, bh/2-30, paint1);
    }
}
