package com.example.databindingdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sc = (LinearLayout) findViewById(R.id.sc);
        InputMethodHelper.assistActivity(this, new InputMethodHelper.OnInputMethodListener() {
            @Override
            public void onInputMethodStatusChanged(Rect keyboardRect, boolean show) {
                View currentFocus = getCurrentFocus();
                if (show && currentFocus != null) {
                    int[] out = new int[2];
                    currentFocus.getLocationInWindow(out);
                    int offset = out[1] + currentFocus.getHeight() - keyboardRect.top;
                    if (offset > 0) {
                        //输入框被遮挡
                        sc.scrollBy(0, offset);
                    }
                }
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) sc.getLayoutParams();
                if (layoutParams.bottomMargin != keyboardRect.height()) {
                    layoutParams.bottomMargin = keyboardRect.height();
                    sc.requestLayout();
                }
            }
        });
    }
}
