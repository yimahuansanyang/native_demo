package com.example.textmore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView text1;
    ImageView mImageView1;
    TextView expandText;
    TextMoreTextView text2;

    boolean isExpand;//是否已展开的状态
    private int maxDescripLine = 3; //TextView默认最大展示行数
    private int deltaValue;//默认高度，即前边由maxLine确定的高度
    private int startValue;//起始高度
    private int durationMillis = 350;//动画持续时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        text1 = (TextView) findViewById(R.id.textView1);
         text2= (TextMoreTextView) findViewById(R.id.text_textView);
        expandText = (TextView) findViewById(R.id.expand_text);

        mImageView1 = (ImageView) findViewById(R.id.expand_view1);
        mImageView1.setOnClickListener(this);

//        text1.setText(getText(R.string.text));
        //第二种可以在这里直接设置文字
         text2.setText(getText(R.string.text));
        //这里大家可以根据实际情况来设置文字的高度，做个判断（可能会文字只有一行，也会占据maxDescripLine行）
//        text1.setHeight(text1.getLineHeight() * maxDescripLine);
//        text1.post(new Runnable() {
//            @Override
//            public void run() {
//                mImageView1.setVisibility(text1.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
//                expandText.setVisibility(text1.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.expand_view1:
                zheDie(text1, mImageView1);
                break;
        }
    }
    private void zheDie(final TextView text, ImageView imageView) {
        isExpand = !isExpand;
        text.clearAnimation();
        startValue = text.getHeight();
        if (isExpand) {
            /**
             * 折叠动画
             * 从实际高度缩回起始高度
             */
            deltaValue = text.getLineHeight() * text.getLineCount() - startValue;
            RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);
            expandText.setText("收起");
        } else {
            /**
             * 展开动画
             * 从起始高度增长至实际高度
             */
            deltaValue = text.getLineHeight() * maxDescripLine - startValue;
            RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);
            expandText.setText("更多");
        }
        Animation animation = new Animation() {
            protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                text.setHeight((int) (startValue + deltaValue * interpolatedTime));
            }
        };
        animation.setDuration(durationMillis);
        text.startAnimation(animation);
    }

}
