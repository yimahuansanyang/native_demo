package com.example.mvpdemo;

public class LoginModel implements LoginInterface.Model {
    @Override
    public String getData() {
        return "网络请求下的数据";
    }
}
