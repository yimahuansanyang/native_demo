package com.example.textmore;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextMoreTextView extends LinearLayout {

    protected TextView contentView;
    protected ImageView expandView;

    protected int textColor;
    protected float textSize;
    protected int maxLine;
    protected String text;

    public int defaultTextColor = Color.BLACK;//默认文字颜色
    public int defaultTextSize = 12;     //默认文字大小
    public int defaultLine = 3;          //默认行数

    public TextMoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initalize();
        initWithAttrs(context, attrs);
        //  bindListener();
    }

    protected void initWithAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MoreTextStyle);
        int textColor = a.getColor(R.styleable.MoreTextStyle_textColor,
                defaultTextColor);
        textSize = a.getDimensionPixelSize(R.styleable.MoreTextStyle_textSize, defaultTextSize);
        maxLine = a.getInt(R.styleable.MoreTextStyle_maxLine, defaultLine);
        text = a.getString(R.styleable.MoreTextStyle_text);
        bindTextView(textColor, textSize, maxLine, text);
        a.recycle();
    }

    protected void initalize() {
        setOrientation(VERTICAL);
        setGravity(Gravity.RIGHT);
        contentView = new TextView(getContext());
        addView(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        expandView = new ImageView(getContext());
        int padding = dip2px(getContext(), 5);
        expandView.setPadding(padding, padding, padding, padding);
        expandView.setImageResource(R.drawable.gengduoshuomingtubiao);
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(expandView, llp);
    }

    protected void bindTextView(int color, float size, final int line, String text) {
        contentView.setTextColor(color);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        contentView.setText(text);
        ViewTreeObserver observer = contentView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //判断写在这个方法里面能拿到contentView.getLineCount()，否则返回时0；
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = contentView.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (contentView.getLineCount() < line) {
                    contentView.setHeight(contentView.getLineHeight() * contentView.getLineCount());
                } else {
                    contentView.setHeight(contentView.getLineHeight() * line);
                    bindListener();//只有在行数大于设定行数才会执行这个方法，做了调整否则会有bug。
                }
                //Log.e("aaa", "bindTextView111: " + contentView.getLineCount());//返回0，为什么
            }
        });

        post(new Runnable() {
            @Override
            public void run() {
                expandView.setVisibility(contentView.getLineCount() > line ? View.VISIBLE : View.GONE);
                //Log.e("aaa", "run: "+contentView.getLineCount() );
            }
        });
    }

    protected void bindListener() {
        setOnClickListener(new OnClickListener() {
            boolean isExpand;

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                contentView.clearAnimation();
                final int deltaValue;
                final int startValue = contentView.getHeight();
                int durationMillis = 350;
                if (isExpand) {
                    deltaValue = contentView.getLineHeight() * contentView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                } else {
                    deltaValue = contentView.getLineHeight() * maxLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        contentView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }

                };
                animation.setDuration(durationMillis);
                contentView.startAnimation(animation);
            }
        });
    }

    public TextView getTextView() {
        return contentView;
    }

    public void setText(CharSequence charSequence) {
        contentView.setText(charSequence);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
