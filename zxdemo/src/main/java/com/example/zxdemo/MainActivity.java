package com.example.zxdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zxdemo.utils.BitmapUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private Button btn_saomiao;
    private Button btn_make_zxcode;
    private EditText ed_content;
    private ImageView result_img;
    private TextView tv_result;
    private int REQUESTCODE = 10;
    private String DECODED_CONTENT_KEY = "11";
    private String DECODED_BITMAP_KEY = "12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btn_saomiao = (Button) findViewById(R.id.btn_saomiao);
        btn_make_zxcode = (Button) findViewById(R.id.btn_make_zxcode);
        ed_content = (EditText) findViewById(R.id.ed_content);
        result_img = (ImageView) findViewById(R.id.result_img);
        tv_result = (TextView) findViewById(R.id.tv_result);

    }

    public void canningMethod(View view) {
        Intent intent = new Intent(MainActivity.this, ZxActivity.class);
        startActivityForResult(intent, REQUESTCODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == 100) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                tv_result.setText("扫描结果： " + content);
            }


        }
    }

    public void makeErweimaMethod(View view) {
        String aimContent = ed_content.getText().toString();
        Create2QR2(aimContent, result_img);
    }

    //生成二维码的方法
    private void Create2QR2(String urls, ImageView imageView) {
        String uri = urls;
        int mScreenWidth = 0;
        Bitmap bitmap;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            mScreenWidth = dm.widthPixels;
            bitmap = BitmapUtil.createQRImage(uri, mScreenWidth, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            //自己写的方法
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<People> listPeople = new ArrayList<People>();
        listPeople.add(new People("张三", 1, "13355556666", 23, "新员工"));
        listPeople.add(new People("张三", 2, "15522223333", 23, "老员工"));
        listPeople.add(new People("李四", 3, "13355556666", 23, "实习生"));
        listPeople.add(new People("提莫", 4, "13311112222", 23, "经理"));
        listPeople.add(new People("张三", 5, "13355556666", 23, "会计"));
        listPeople.add(new People("德玛", 6, "3344", 23, "开发"));
        listPeople.add(new People("卡特", 7, "13355556666", 23, "测试"));
        listPeople.add(new People("提莫", 8, "13355556666", 23, "美工"));
        listPeople.add(new People("提莫", 9, "13311112222", 23, "实施"));
        listPeople.add(new People("卡兹克", 10, "13356786666", 23, "售前"));
        listPeople.add(new People("亚索", 11, "13355556666", 23, "销售"));

        Set<People> setData = new HashSet<People>();
        setData.addAll(listPeople);

        System.out.println("list- size----" + listPeople.size());
        System.out.println("list-----" + listPeople.toString());

        System.out.println("set- size----" + setData.size());
        System.out.println("set-----" + setData.toString());

        for (People pp : setData) {
            System.out.println("p--" + pp.toString());
        }
    }
}
