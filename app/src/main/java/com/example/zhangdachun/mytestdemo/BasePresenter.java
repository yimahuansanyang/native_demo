package com.example.zhangdachun.mytestdemo;

import android.view.View;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    private WeakReference<V> weakView;
    protected M model;

    public V getView() {
        return (V) weakView;
    }

    /**
     * 用于检查View是否为空对象
     *
     * @return
     */
    public boolean isAttachView() {
        return this.weakView != null && this.weakView.get() != null;
    }
    @Override
    public void attchView(V view) {
        if (weakView==null){
            this.weakView = new WeakReference<V>(view);
        }

    }

  

    @Override
    public void detchView() {
        if (this.weakView != null) {
            this.weakView.clear();
            this.weakView = null;
        }
    }
}
