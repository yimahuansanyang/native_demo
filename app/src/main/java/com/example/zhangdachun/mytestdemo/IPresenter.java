package com.example.zhangdachun.mytestdemo;

public interface IPresenter<V extends IView> {
    /*p层的接口*/
    void attchView(V view);
    void detchView();
}
