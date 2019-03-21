package com.example.retrolfitdemo.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrolfitdemo.R;
import com.example.retrolfitdemo.face.DownloadListener;
import com.example.retrolfitdemo.permission.KbPermission;
import com.example.retrolfitdemo.permission.KbPermissionListener;
import com.example.retrolfitdemo.permission.KbPermissionUtils;
import com.example.retrolfitdemo.utils.DownloadUtil;
import com.example.retrolfitdemo.utils.KbWithWordsCircleProgressBar;
import com.example.retrolfitdemo.utils.SystemUtil;
import com.squareup.picasso.Picasso;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_pic;
    private static final String TAG = "DownloadPictureActivity";
    private static final String PICTURE_URL = "http://small-bronze.oss-cn-shanghai.aliyuncs.com/" +
            "image/video/cover/2018/3/8/8BBC6C00DF78476C98AD9CA482DEF635.jpg";
    private PictureActivity mContext;
    private FrameLayout mBackLayout;
    private ImageView mPicture;
    private FrameLayout mCircleProgressLayout;
    private KbWithWordsCircleProgressBar mCircleProgress;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mCircleProgressLayout.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    int progress = (int) msg.obj;
                    mCircleProgress.setProgress(progress);
                    break;
                case 2:
                    mCircleProgressLayout.setVisibility(View.GONE);
                    String urlStr = (String) msg.obj;
                    if (!TextUtils.isEmpty(urlStr)) {
                        Picasso.with(mContext).load((String) (msg.obj)).placeholder(R.mipmap.ic_launcher)
                                .error(android.R.drawable.ic_menu_delete)
                                .noFade()
                                .resize(100, 100)
                                .centerInside()
                                .into(mPicture);    //需要加载图片的控件
                    }


                    //加载
                    break;
                case 3:
                    mCircleProgressLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_picture);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SystemUtil.setLightStatusBar(this, Color.WHITE);
        }

        mContext = this;
        TextView toolbarTitle = findViewById(R.id.tv_toolbar_title);
        toolbarTitle.setText(R.string.download_picture);
        mBackLayout = findViewById(R.id.btn_back);
        mBackLayout.setOnClickListener(this);
        mPicture = findViewById(R.id.iv_picture);
        mCircleProgressLayout = (FrameLayout) findViewById(R.id.fl_circle_progress);
        mCircleProgress = (KbWithWordsCircleProgressBar) findViewById(R.id.circle_progress);

        if (KbPermissionUtils.needRequestPermission()) {
            KbPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .callBack(new KbPermissionListener() {
                        @Override
                        public void onPermit(int requestCode, String... permission) {
                            downloadPicture();
                        }

                        @Override
                        public void onCancel(int requestCode, String... permission) {
                            KbPermissionUtils.goSetting(mContext);
                        }
                    })
                    .send();
        } else {
            downloadPicture();
        }
    }

    private void downloadPicture() {
        //下载相关
        DownloadUtil downloadUtil = new DownloadUtil();
//
        downloadUtil.downLoadFile(PICTURE_URL, new DownloadListener() {
            @Override
            public void onStart() {
                sendMsg(0, 0);
            }

            @Override
            public void onProgress(int currentLenth) {
                sendMsg(1, currentLenth);

            }

            @Override
            public void onFinish(String path) {
                sendPathMsg(2, path);

            }

            @Override
            public void onFailure() {
                sendMsg(3, 0);

            }
        });

    }

    private void sendPathMsg(int i, String i1) {
        Message message = new Message();
        message.what = i;
        message.obj = i1;
        mHandler.sendMessage(message);

    }

    private void sendMsg(int i, int progress) {
        Message message = new Message();
        message.what = i;
        message.obj = progress;
        mHandler.sendMessage(message);
    }

    @Override
    public void onClick(View view) {
        if (view == mBackLayout) {
            finish();
        }

    }
}
