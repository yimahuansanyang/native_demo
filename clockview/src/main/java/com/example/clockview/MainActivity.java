package com.example.clockview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ClockView clockview;
    private Button btn_img;
    private ImageView iv_img;
    private String path = "http://192.168.31.184:8011/main.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clockview = (ClockView) findViewById(R.id.clockview);
        btn_img = (Button) findViewById(R.id.btn_img);
        iv_img = (ImageView) findViewById(R.id.iv_img);
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = getBitmap(path);
                            if (bitmap == null) {
                                return;
                            }
                            iv_img.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("TAG", e.getMessage());
                        }
                    }
                }).start();

            }
        });
    }

    private Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
