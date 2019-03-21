package com.example.mvpdemo;

public class LoginModelImpl implements LoginModel1 {
    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    if (username.equals("David") && password.equals("12345")) {
                        onLoginListener.loginSuccess(new User(username, password));
                    } else {
                        onLoginListener.loginFailed("Incorrect username or password.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
