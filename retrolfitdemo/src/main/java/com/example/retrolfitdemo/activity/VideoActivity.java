package com.example.retrolfitdemo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.retrolfitdemo.R;
import com.example.retrolfitdemo.face.DownloadListener;
import com.example.retrolfitdemo.permission.KbPermission;
import com.example.retrolfitdemo.permission.KbPermissionListener;
import com.example.retrolfitdemo.permission.KbPermissionUtils;
import com.example.retrolfitdemo.utils.DownloadUtil;
import com.example.retrolfitdemo.utils.KbWithWordsCircleProgressBar;
import com.example.retrolfitdemo.utils.SystemUtil;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl_circle_progress;
    private SimpleExoPlayerView simpleExoPlayerView;
    private KbWithWordsCircleProgressBar circle_progress;
    private TextView tv_count_timer;
    private static final String PLAY_VIDEO_URL =
            "http://c1.daishumovie.com/7d2b85ce0799dacb96c3949e25d74678/5aa40100/video/client/2018/1/9/3F8F87CC040147F28AC97A4733491A8A_640x360_200_48_24.mp4";
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private VideoActivity mContext;
    private FrameLayout mBackLayout;
    private DefaultExtractorsFactory mExtractorsFactory;
    protected SimpleExoPlayer mPlayer;
    private DataSource.Factory mMediaDataSourceFactory;

    //下载相关
    private DownloadUtil mDownloadUtil;
    private String mVideoPath; //下载到本地的视频路径
    private boolean mIsBackground; //是否进入后台

    private String TAG = VideoActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_video);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SystemUtil.setLightStatusBar(this, Color.WHITE);
        }

        mContext = this;
        initViews();
    }

    private void initViews() {
        TextView toolbaritle = findViewById(R.id.tv_toolbar_title);
        toolbaritle.setText(R.string.download_video);
        mBackLayout = findViewById(R.id.btn_back);
        mBackLayout.setOnClickListener(this);
        fl_circle_progress = (FrameLayout) findViewById(R.id.fl_circle_progress);
        simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.simpleExoPlayerView);
        circle_progress = (KbWithWordsCircleProgressBar) findViewById(R.id.circle_progress);
        tv_count_timer = (TextView) findViewById(R.id.tv_count_timer);
        initVido();
        if (KbPermissionUtils.needRequestPermission()) { //判断是否需要动态申请权限
            KbPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE) //需要申请的权限(支持不定长参数)
                    .callBack(new KbPermissionListener() {
                        @Override
                        public void onPermit(int requestCode, String... permission) { //允许权限的回调
                            downloadVideo(); //处理具体下载过程
                        }

                        @Override
                        public void onCancel(int requestCode, String... permission) { //拒绝权限的回调
                            KbPermissionUtils.goSetting(mContext); //跳转至当前app的权限设置界面
                        }
                    })
                    .send();
        } else {
            downloadVideo(); //处理具体下载过程
        }
    }

    private void downloadVideo() {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downLoadFile(PLAY_VIDEO_URL, new DownloadListener() {
            @Override
            public void onStart() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fl_circle_progress.setVisibility(View.VISIBLE);

                    }
                });
            }

            @Override
            public void onProgress(final int currentLenth) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        circle_progress.setProgress(currentLenth);
                    }
                });

            }

            @Override
            public void onFinish(final String path) {
                mVideoPath = path;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fl_circle_progress.setVisibility(View.GONE);
                        //下载文件完成后 进行播放
                        if (mPlayer == null) {
                            return;
                        }
                        mPlayer.prepare(createMediaSource(mVideoPath));
                        simpleExoPlayerView.setPlayer(mPlayer);
                        mPlayer.setPlayWhenReady(true);
                    }
                });

            }

            @Override
            public void onFailure() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fl_circle_progress.setVisibility(View.GONE);
                    }
                });

            }
        });
    }

    private void initVido() {
        if (mMediaDataSourceFactory == null) {
            mMediaDataSourceFactory = buildDataSourceFactory(this, false);
        }
        if (mExtractorsFactory == null) {
            mExtractorsFactory = new DefaultExtractorsFactory();
        }
        if (mPlayer == null) {
            mPlayer = createNewPlayer(this);
            mPlayer.addListener(eventListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsBackground && mPlayer != null && !TextUtils.isEmpty(mVideoPath)) {
            mIsBackground = false;
            mPlayer.prepare(createMediaSource(mVideoPath));
            simpleExoPlayerView.setPlayer(mPlayer);
            mPlayer.setPlayWhenReady(true);
        }
        if (mPlayer != null && mPlayer.getCurrentPosition() > 0) {
            mPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsBackground = true;
        if (mPlayer != null && mPlayer.getPlayWhenReady()) {
            mPlayer.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onClick(View view) {
        if (mBackLayout == view) {
            finish();
        }
    }

    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler();

    //播放事件监听
    private ExoPlayer.EventListener eventListener = new ExoPlayer.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            System.out.println("播放: onTimelineChanged 周期总数 " + timeline);
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            System.out.println("播放: TrackGroupArray  ");
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            switch (playbackState) {
                case Player.STATE_ENDED:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPlayer.prepare(createMediaSource(mVideoPath));
                            simpleExoPlayerView.setPlayer(mPlayer);
//                            simpleExoPlayerView.setUseController(false);
                            mPlayer.setPlayWhenReady(true);
                        }
                    }, 3000);
                    break;
            }
        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e(TAG, "onPlayerError: " + "播放: onPlayerError");
        }

        @Override
        public void onPositionDiscontinuity() {
            Log.e(TAG, "onPositionDiscontinuity: ");
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }
    };

    private SimpleExoPlayer createNewPlayer(Context context) {
        TrackSelection.Factory adaptiveTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(adaptiveTrackSelectionFactory);
        return ExoPlayerFactory.newSimpleInstance(context, trackSelector);
    }

    private DataSource.Factory buildDataSourceFactory(Context context, boolean useBandwidthMeter) {
        return buildDataSourceFactory(context, useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    private DataSource.Factory buildDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(context, bandwidthMeter, buildHttpDataSourceFactory(context, bandwidthMeter));
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        String httpUserAgent = Util.getUserAgent(context, "ExoPlayer");
        return new DefaultHttpDataSourceFactory(httpUserAgent, bandwidthMeter);
    }

    private MediaSource createMediaSource(String... playUrls) {
        MediaSource[] ma = new MediaSource[playUrls.length];
        int i = 0;
        for (String url : playUrls) {
            ma[i++] = new ExtractorMediaSource(Uri.parse(url),
                    mMediaDataSourceFactory, mExtractorsFactory, null, null);
        }
        return new ConcatenatingMediaSource(ma);
    }
}
