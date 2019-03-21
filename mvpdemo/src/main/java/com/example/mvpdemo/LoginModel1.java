package com.example.mvpdemo;

public interface LoginModel1 {
    void login(String username, String password, OnLoginListener onLoginListener);

    interface OnLoginListener {
        void loginSuccess(User user);

        void loginFailed(String s);
    }
}
