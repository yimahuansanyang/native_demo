package com.example.mvpdemo;

public class LoginPresenter implements LoginInterface.Presenter {
    private final LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        this.model = new LoginModel();
    }

    /*实现view与model层的交互*/
    @Override
    public void loadData() {
        String data = model.getData();
        view.setData(data);
    }
}
