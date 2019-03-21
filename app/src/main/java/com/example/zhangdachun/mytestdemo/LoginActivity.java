package com.example.zhangdachun.mytestdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends MvpActivity<ILoginView, LoginPresenter> implements  ILoginView {

    private Button btn_login;
    private EditText ed_user_password;
    private EditText ed_user_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        ed_user_password = (EditText) findViewById(R.id.ed_user_password);
        ed_user_name = (EditText) findViewById(R.id.ed_user_name);
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    public void login(View view) {
        String name = ed_user_name.getText().toString();
        String pwd = ed_user_password.getText().toString();
        getPresenter().login(name, pwd);
    }
    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onFailed() {

    }
}
