package com.example.zhangdachun.mytestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextView tv_weather;
    private MainPresenter mainPresenter;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        pb = (ProgressBar) findViewById(R.id.pb);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loaddata(new ResultCallBacl() {
            @Override
            public void onSuccess(Object weatherinfoEntity) {
                if (weatherinfoEntity != null) {
                    tv_weather.setText(weatherinfoEntity + "");
                }
            }

            @Override
            public void onFailed() {

            }
        });

    }

    @Override
    public void showData(Object mainModel) {
        tv_weather.setText(mainModel + "");
    }

    @Override
    public void showProgress() {
        pb.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        pb.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
