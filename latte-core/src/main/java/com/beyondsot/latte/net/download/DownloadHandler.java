package com.beyondsot.latte.net.download;

import android.os.AsyncTask;

import com.beyondsot.latte.net.RestCreator;
import com.beyondsot.latte.net.callback.IError;
import com.beyondsot.latte.net.callback.IFailure;
import com.beyondsot.latte.net.callback.IRequest;
import com.beyondsot.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件下載
 */
public class DownloadHandler {
    private final String URL; //下載路徑
    private final WeakHashMap<String, Object> PARAMS;  //參數
    private final IRequest REQUEST; //開始下載
    private final String DOWNLOAD_DIR; //下載存放位置
    private final String EXTENSION; // 文件後綴
    private final String NAME; //文件名稱
    private final ISuccess SUCCESS; // 下載成功
    private final IFailure FAILURE; // 下載失敗
    private final IError ERROR; //下載異常

    public DownloadHandler(String url,
                           WeakHashMap<String, Object> params,
                           IRequest request,
                           String downDir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator
                .getRestService()
                .download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        // 下載成功,異步下載，進行緩存
                        if (response.isSuccessful()) {
                            ResponseBody body = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, body, NAME);
                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //下載失敗
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });

    }


}
