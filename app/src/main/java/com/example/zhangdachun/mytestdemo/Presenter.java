package com.example.zhangdachun.mytestdemo;

public interface Presenter<V> {
    void attachView(V view);

    void detachView();

}
