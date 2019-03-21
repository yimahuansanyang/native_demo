package com.example.zhangdachun.mytestdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class MvpActivity<V extends IView, P extends IPresenter<V>> extends AppCompatActivity implements IView {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.attchView((V) this);
    }

    protected P getPresenter() {
        return presenter;
    }

    protected void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    protected V getView() {
        return (V) this;
    }

    @Override
    protected void onDestroy() {
        presenter.detchView();
        super.onDestroy();
    }
}
