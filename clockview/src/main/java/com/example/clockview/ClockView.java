package com.example.clockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class ClockView extends View {
    private Date time;
    private float centerX, centerY, radius;//圆心坐标以及半径
    private float width, height; //视图宽高
    private float lengthOfHourScale; // 时刻度线长度
    private float lengthOfMinuteScale; // 分刻度线长度
    private float lengthOfHourHand;  // 时针长度
    private float lengthOfMinuteHand;  // 分针长度
    private float lengthOfSecondHand;  // 秒针长度
    /**
     * 各种画笔
     */
    private Paint paintCircle, paintScaleHour, paintScaleMinute, paintHourHand, paintSecondHand, paintMinuteHand, paintBrand, paintNum;
    private boolean is24HourSystem; // 小时制（24、12）
    private int currentHour;
    private int currentMinute;

    public ClockView(Context context) {
        super(context);
        initView(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化画笔、刻度长度、指针长度及时间
     *
     * @param context
     */
    public void initView(Context context) {
        //外圆画笔
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5f);
        paintCircle.setColor(Color.BLACK);

        paintBrand = new Paint();
        paintBrand.setStyle(Paint.Style.STROKE);
        paintBrand.setAntiAlias(true);
        paintBrand.setStrokeWidth(2f);
        paintBrand.setTextSize(15);
        paintBrand.setColor(Color.BLACK);

        //时刻度画笔
        paintScaleHour = new Paint();
        paintScaleHour.setStyle(Paint.Style.STROKE);
        paintScaleHour.setAntiAlias(true);
        paintScaleHour.setStrokeWidth(3.5f);
        paintScaleHour.setColor(Color.BLACK);

        //分刻度画笔
        paintScaleMinute = new Paint();
        paintScaleMinute.setStyle(Paint.Style.STROKE);
        paintScaleMinute.setAntiAlias(true);
        paintScaleMinute.setStrokeWidth(1.5f);
        paintScaleMinute.setTextSize(10);
        paintScaleMinute.setColor(Color.BLACK);

        //时针画笔
        paintHourHand = new Paint();
        paintHourHand.setStyle(Paint.Style.STROKE);
        paintHourHand.setAntiAlias(true);
        paintHourHand.setStrokeWidth(5f);
        paintHourHand.setColor(Color.RED);

        //分针画笔
        paintMinuteHand = new Paint();
        paintMinuteHand.setStyle(Paint.Style.STROKE);
        paintMinuteHand.setAntiAlias(true);
        paintMinuteHand.setStrokeWidth(3f);
        paintMinuteHand.setColor(Color.BLUE);

        //秒针画笔
        paintSecondHand = new Paint();
        paintSecondHand.setStyle(Paint.Style.STROKE);
        paintSecondHand.setAntiAlias(true);
        paintSecondHand.setStrokeWidth(1.5f);
        paintSecondHand.setColor(Color.RED);

        paintNum = new Paint();
        paintNum.setStyle(Paint.Style.STROKE);
        paintNum.setAntiAlias(true);
        paintNum.setStrokeWidth(1.5f);
        paintNum.setTextSize(20);
        paintNum.setColor(Color.BLACK);

        //系统时间
        time = new Date(System.currentTimeMillis());
        is24HourSystem = false; // 12小时制
        lengthOfHourHand = 200f;  //时针长度
        lengthOfMinuteHand = 300f; //分针长度
        lengthOfSecondHand = 400f; //秒针长度

        lengthOfHourScale = 60f;  //时刻度长度
        lengthOfMinuteScale = 30f; //分刻度长度

        currentHour = 0;
        currentMinute = 0;
    }

    @Override
    protected void onFinishInflate() {
        Log.e(getClass().getSimpleName(), "onFinishInflate()");
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(getClass().getSimpleName(), "onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 此处确定宽高、圆心、半径
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(getClass().getSimpleName(), "onLayout()");
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        centerX = width / 2;
        centerY = height / 2;
        radius = Math.min(width / 2, height / 2);
        Log.e(getClass().getSimpleName(), "height = " + height + "    width = " + width + "    centerY = " + centerY + "    centerX = " + centerX);
    }

    @Override
    protected void onAttachedToWindow() {
        Log.e(getClass().getSimpleName(), "onAttachedToWindow()");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int hours = is24HourSystem == true ? 24 : 12;
        int divisions = is24HourSystem == true ? 120 : 60; // 分割线数量

        //Step 1: 画外圆
        drawCircle(centerX, centerY, radius, canvas);
        //Step 2: 画刻度线及其刻度值
        drawScaleValue(centerX, centerY, radius, divisions, canvas);
        //Setp 3:画时针分针
        drawHand(centerX, centerY, radius + 2, divisions, canvas);
        //画手表品牌
        drawBrand(centerX -5, centerY / 2, "TISSOT", canvas);
        drawBrand(centerX+5 , centerY / 2+20, "1853", canvas);
        drawPoint(centerX, centerY / 2, canvas);
        invalidate();
    }

    private void drawPoint(float centerX, float v, Canvas canvas) {
        canvas.drawPoint(centerX,
                centerY, paintHourHand);
    }

    private void drawBrand(float v, float centerY, String brand, Canvas canvas) {
        canvas.drawText(brand, v - 15,
                centerY - 20, paintBrand);

    }

    /**
     * 描绘时钟外圆
     *
     * @param x
     * @param y
     * @param radius
     */
    protected void drawCircle(float x, float y, float radius, Canvas canvas) {
        canvas.drawCircle(x, y, radius, paintCircle);
    }

    /**
     * 描绘刻度值
     *
     * @param x
     * @param y
     * @param radius
     */
    protected void drawScaleValue(float x, float y, float radius, int divisions, Canvas canvas) {
        int divisionPercent = 360 / divisions;
        for (int i = 0; i < divisions; i++) {
            if (i % 5 == 0) { //时刻度
                int hourValue = i / 5 == 0 ? 12 : i / 5;
                String degreeStr = String.valueOf(hourValue);
                canvas.drawLine(centerX, centerY - radius, centerX, centerY - radius + lengthOfHourScale, paintHourHand);
                canvas.drawText(degreeStr, centerX - paintScaleHour.measureText(degreeStr) / 2, centerY - radius + lengthOfHourScale + paintScaleHour.measureText(degreeStr) / 2 + 15, paintNum);
            } else { //分刻度
                canvas.drawLine(centerX, centerY - radius, centerX, centerY - radius + lengthOfMinuteScale, paintScaleMinute);
            }
            //通过旋转画布简化坐标运算
            canvas.rotate(divisionPercent, centerX, centerY);
        }
    }


    /**
     * 描绘时针分针
     *
     * @param x
     * @param y
     * @param radius
     */
    protected void drawHand(float x, float y, float radius, int divisions, Canvas canvas) {
//        Log.e(getClass().getSimpleName(),"drawHand() begin");
        time = new Date(System.currentTimeMillis());
        canvas.save(); //保存之前的画布
        // 将坐标原点平移到圆心
        canvas.translate(x, y);

        //Step 1: 计算时针、分针、秒针偏移弧度（相对于12点）
        float divisionPercent = 360.0f / divisions;
        float secondPercent = divisionPercent * (time.getSeconds() % 60.0f);  //秒指针偏移量（相对于12点的偏移量）
        float minutePercentRelativeToHour = (secondPercent / 360.0f) * divisionPercent + divisionPercent * time.getMinutes(); //分指针偏移量（相对于时针的偏移量）
        float hourPercent = (time.getMinutes() / 60.0f) * divisionPercent * 5 + divisionPercent * (divisions / 12) * (time.getHours() % 12.0f); // 时针偏移量（相对于12点的偏移量）
//        float minutePercent = minutePercentRelativeToHour + divisionPercent * (divisions / 12) * (time.getHours() % 12.0f);
        Log.e(getClass().getSimpleName(), "drawHand()   hourPercent = " + hourPercent + "    minutePercent = " + minutePercentRelativeToHour + "    secondPercent = " + secondPercent + "    minutePercentRelativeToHour = " + minutePercentRelativeToHour);

        //Step 2: 将时钟角度转换至Android系统绘制坐标系的角度（顺时针旋转90度，X、Y坐标互换）
        //        并转换成弧度
        float AndroidHourAngel = hourPercent - 90;
        float AndroidMinuteAngel = minutePercentRelativeToHour - 90;
        float AndroidSecondAngel = secondPercent - 90;
        //将角度转换成弧度
        double HourRadians = Math.toRadians(AndroidHourAngel);       //小时弧度
        double MinuteRadians = Math.toRadians(AndroidMinuteAngel);   //分钟弧度
        double SecondRadians = Math.toRadians(AndroidSecondAngel);   //秒钟弧度
        Log.e(getClass().getSimpleName(), "drawHand()   AndroidHourAngel = " + AndroidHourAngel + "    AndroidMinuteAngel = " + AndroidMinuteAngel + "    AndroidSecondAngel = " + AndroidSecondAngel);

        //Step 3: 计算坐标
        float stopHourX = (float) (lengthOfHourHand * Math.cos(HourRadians));
        float stopHourY = (float) (lengthOfHourHand * Math.sin(HourRadians));
        float stopMinuteX = (float) (lengthOfMinuteHand * Math.cos(MinuteRadians));
        float stopMinuteY = (float) (lengthOfMinuteHand * Math.sin(MinuteRadians));
        float stopSecondX = (float) (lengthOfSecondHand * Math.cos(SecondRadians));
        float stopSecondY = (float) (lengthOfSecondHand * Math.sin(SecondRadians));
        Log.e(getClass().getSimpleName(), "drawHand()  stopHourX = " + stopHourX + "   stopHourY = " + stopHourY + "   stopMinuteX = " + stopMinuteX + "   stopMinuteY = " + stopMinuteY + "   stopSecondX = " + stopSecondX + "   stopSeconY = " + stopSecondY);

        //Step 4: 画线
        canvas.drawLine(0, 0, stopHourX / 2, stopHourY / 2, paintHourHand); // 画时针
        canvas.drawLine(0, 0, stopMinuteX / 2, stopMinuteY / 2, paintMinuteHand); // 画分针
        canvas.drawLine(0, 0, stopSecondX / 2, stopSecondY / 2, paintSecondHand); // 画秒钟

        canvas.restore();
    }

    /**
     * 重置时间
     *
     * @param time
     */
    protected void reset(Date time) {

    }

    protected void verifyTime() {

    }
}
