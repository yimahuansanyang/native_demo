package com.example.retrolfitdemo.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.example.retrolfitdemo.face.ApiInterface;
import com.example.retrolfitdemo.face.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadUtil {
    private static final String TAG = "DownloadUtil";
    private static final String PATH_CHALLENGE_VIDEO = Environment.getExternalStorageDirectory() + "/DownloadFile";
    private ApiInterface mApi;
    private String mVideoPath; //下载到本地的视频路径
    private File mFile;
    private Call<ResponseBody> call;
    private Thread mThread;
    private FileOutputStream fos;

    public DownloadUtil() {
        if (mApi == null) {
            mApi = ApiHelper.getInstance().buildRetrofit("https://sapi.daishumovie.com/")
                    .creatService(ApiInterface.class);
        }
    }

    /*@pram 下载文件
     * @pram URL资源定位符
     * @pram 下载监听
     * */
    public void downLoadFile(String url, final DownloadListener downloadListener) {
        String name = url;
        if (FileUtils.createOrExistsDir(PATH_CHALLENGE_VIDEO)) {
            int i = name.lastIndexOf('/');
            if (i != -1) {
                name = name.substring(i);
                mVideoPath = PATH_CHALLENGE_VIDEO + name;

            }
        }

        if (TextUtils.isEmpty(mVideoPath)) {
            return;
        }
        mFile = new File(mVideoPath);
        if (!FileUtils.isFileExists(mFile) && FileUtils.createOrExistsFile(mFile)) {
            if (mApi == null) {
                return;

            }
            call = mApi.downLoadFile(url);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    //请求成功的回调。下载文件放在子线程里
                    mThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            writeFile2Disk(response, mFile, downloadListener);
                        }
                    });
                    mThread.start();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    downloadListener.onFailure();

                }
            });

        } else {
            downloadListener.onFinish(mVideoPath);
        }
    }


    //保存到sdcard
    public void writeFile2Disk(Response<ResponseBody> response, File mFile, DownloadListener downloadListener) {
        downloadListener.onStart();
        long currentLength = 0;
        OutputStream fos = null;

        if (response == null) {
            return;
        }
        InputStream inputStream = response.body().byteStream();
        long totalLength = response.body().contentLength();
        try {
            fos = new FileOutputStream(mFile);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                currentLength += len;
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
                if ((int) (100 * currentLength / totalLength) == 100) {
                    downloadListener.onFinish(mVideoPath);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
