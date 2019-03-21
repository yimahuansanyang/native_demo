package com.example.dialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn_d1;
    private String[] itemname = {"android", "java", "python"};
    private boolean[] b = {true, false, false};
    private boolean isdis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_d1 = (Button) findViewById(R.id.btn_d1);
        btn_d1.setOnClickListener(new View.OnClickListener() {

            private AlertDialog dialog;

            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        switch (keyEvent.getAction()) {
                            case KeyEvent.ACTION_DOWN:
                                isdis = false;
                                break;
                            case KeyEvent.ACTION_UP:
                                isdis = false;
                                break;
                            case KeyEvent.ACTION_MULTIPLE:
                                isdis = false;
                                break;
                            case KeyEvent.KEYCODE_BACK:
                                isdis = true;
                                break;
                            case KeyEvent.KEYCODE_HOME:
                                isdis = true;
                                break;
                        }

                        return isdis;
                    }
                });
//                builder.setMultiChoiceItems(itemname, b, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
////                        dialog.dismiss();
//                    }
//                });
                builder.setSingleChoiceItems(itemname, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog = builder.create();
                dialog.show();

            }
        });
    }

    public void progressdialog(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("标题"); // 设置标题
        progressDialog.setMessage("加载中..."); // 设置消息
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        progressDialog.setView(imageView);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setIndeterminate(true);
        new Thread(new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                for (int i = 0; i < 101; i++) {
                    try {
                        progress++;
//                        NumberFormat percentInstance = NumberFormat.getPercentInstance(Locale.CHINA);
//                        progressDialog.setProgressNumberFormat("%1dkb/%2dkb");
//                        progressDialog.setProgressPercentFormat(percentInstance);
                        progressDialog.setProgress(progress);
                        Thread.sleep(50);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();

            }
        }).start();
        progressDialog.show(); // 显示进度条

    }
}
