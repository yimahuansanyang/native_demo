package com.example.mvpdemo;

public interface LoginInterface {
    interface View {
        void setData(String data);
    }

    interface Model {
        String getData();
    }

    interface Presenter {
        void loadData();
    }
}
