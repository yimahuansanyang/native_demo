package com.example.retrolfitdemo.face;

public interface DownloadListener {
    //开始
    void onStart();

    //下载中
    void onProgress(int currentLenth);
    //结束

    void onFinish(String path);

    //失败
    void onFailure();

}
