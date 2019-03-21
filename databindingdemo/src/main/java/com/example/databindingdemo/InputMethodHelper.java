package com.example.databindingdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

@TargetApi(14)
public class InputMethodHelper {


    private static int SHOW_FORCED = 1;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private OnInputMethodListener onInputMethodListener;
    private Rect windowContentRect;
    private Rect keyboardRect;

    private InputMethodHelper(OnInputMethodListener listener) {
        this.onInputMethodListener = listener;
    }

    private void onAttach(Activity activity) {
        final View decorView = activity.getWindow().getDecorView();
        windowContentRect = getDisplayVisibleFrameHeight(decorView);
        onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect displayVisibleFrame = getDisplayVisibleFrameHeight(decorView);
                if (keyboardRect == null) {
                    keyboardRect = new Rect(displayVisibleFrame);
                }
                keyboardRect.top = displayVisibleFrame.bottom;
                keyboardRect.bottom = windowContentRect.bottom;
                if (onInputMethodListener != null) {
                    onInputMethodListener.onInputMethodStatusChanged(keyboardRect, keyboardRect.height() != 0);
                }
            }
        };
        decorView
                .getViewTreeObserver()
                .addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    @RequiresApi(16)
    private void onDetach(Activity activity) {
        if (onInputMethodListener != null) {
            activity.getWindow()
                    .getDecorView()
                    .getViewTreeObserver()
                    .removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void assistActivity(final Activity host, OnInputMethodListener onInputMethodListener) {
        if (host == null) {
            return;
        }
        final InputMethodHelper methodHelper = new InputMethodHelper(onInputMethodListener);
        host.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (host == activity && methodHelper.onGlobalLayoutListener == null) {
                    methodHelper.onAttach(activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (host == activity && methodHelper.onGlobalLayoutListener == null) {
                    throw new IllegalStateException("assistActivity() must be called before onStart() called!");
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activity == host) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        methodHelper.onDetach(activity);
                    }
                    activity.getApplication().unregisterActivityLifecycleCallbacks(this);
                }
            }
        });
    }

    public static void assistFragment(final Fragment fragment, OnInputMethodListener onInputMethodListener) {
        if (fragment == null) {
            return;
        }
        final InputMethodHelper methodHelper = new InputMethodHelper(onInputMethodListener);
        fragment.getFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
                if (f == fragment) {
                    methodHelper.onAttach(f.getActivity());
                }
            }

            @Override
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                if (f == fragment) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        methodHelper.onDetach(f.getActivity());
                    }
                    f.getFragmentManager().unregisterFragmentLifecycleCallbacks(this);
                }
            }
        }, false);
    }

    public static Rect getDisplayVisibleFrameHeight(View view) {
        Rect r = new Rect();
        view.getWindowVisibleDisplayFrame(r);
        return r;
    }

    public static void toggleInputMethod(View token, boolean show) {
        if (token == null)
            return;
        InputMethodManager manager = (InputMethodManager) token.getContext().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        token.setFocusableInTouchMode(show);
        token.setFocusable(show);
        if (token instanceof EditText) {
            ((EditText) token).setCursorVisible(show);
        }
        if (show) {
            if (!token.isFocused()) {
                token.requestFocus();
                if (token instanceof EditText) {
                    int length = ((EditText) token).getText().length();
                    ((EditText) token).setSelection(length);
                }
            }
            if (manager != null) {
                manager.showSoftInput(token, SHOW_FORCED);
            }
        } else {
            token.clearFocus();
            if (manager != null) {
                manager.hideSoftInputFromWindow(token.getWindowToken(), 0);
            }
        }
    }

    public interface OnInputMethodListener {
        /**
         * 软键盘弹出/收起监听
         *
         * @param keyboardRect 键盘弹出区域，宽，高
         *                     left = keyboardRect.left
         *                     top = keyboardRect.top
         *                     right = keyboardRect.right
         *                     bottom = keyboardRect.bottom
         *                     width = keyboardRect.width()
         *                     height = keyboardRect.height()
         * @param show         true 显示，false 隐藏
         */
        void onInputMethodStatusChanged(Rect keyboardRect, boolean show);
    }
}
