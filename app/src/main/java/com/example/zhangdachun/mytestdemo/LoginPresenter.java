package com.example.zhangdachun.mytestdemo;

public class LoginPresenter extends BasePresenter<ILoginView, LoginModel> {
    public LoginPresenter() {
        model = new LoginModel();
    }

    public void login(String name, String password) {
        model.Login(new ILoginView() {
            @Override
            public void onLoginSuccess() {
                if (((ILoginView) getView()) != null) {
                    ((ILoginView) getView()).onLoginSuccess();
                }
            }

            @Override
            public void onFailed() {
                if (((ILoginView) getView()) != null)
                    ((ILoginView) getView()).onFailed();
            }
        });
    }

}
