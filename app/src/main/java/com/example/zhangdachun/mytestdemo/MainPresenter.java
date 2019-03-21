package com.example.zhangdachun.mytestdemo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;
    private static int HTTP_200 = 200;
    private static String TAG_GET = "GET";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 520) {
                mainView.showData(msg.obj);
                mainView.hideProgress();
            }
        }
    };
    /**
     * weatherinfo : {"city":"北京","cityid":"101010100","temp":"10","WD":"东南风","WS":"2级","SD":"26%","WSE":"2","time":"10:25","isRadar":"1","Radar":"JC_RADAR_AZ9010_JB","njd":"暂无实况","qy":"1012"}
     */

    private WeatherinfoEntity weatherinfo;

    @Override
    public void attachView(MainView view) {
        this.mainView = view;

    }

    @Override
    public void detachView() {
        this.mainView = null;

    }

    public void loaddata(final ResultCallBacl resultCallBacl) {
        mainView.showProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    requestByGet(resultCallBacl);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void requestByGet(ResultCallBacl resultCallBacl) throws Exception {
        String path = "http://www.weather.com.cn/adat/sk/101010100.html";
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // 开始连接
        urlConn.connect();
        // 判断请求是否成功
        if (urlConn.getResponseCode() == HTTP_200) {
            // 获取返回的数据
            InputStream inputStream = urlConn.getInputStream();
            String result = StreamUtils.parseSteam(urlConn.getInputStream());
            JSONObject jb = new JSONObject(result);
            JSONObject weatherinfo = jb.getJSONObject("weatherinfo");
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                Thread.currentThread().getName();
            }
            Message message = new Message();
            message.obj = weatherinfo;
            message.what = 520;
            mHandler.sendMessage(message);
//            resultCallBacl.onSuccess(jb);
            Log.i(TAG_GET, "Get方式请求成功，返回数据如下：" + result);
        } else {
            Log.i(TAG_GET, "Get方式请求失败");
        }
        // 关闭连接
        urlConn.disconnect();
    }

}
