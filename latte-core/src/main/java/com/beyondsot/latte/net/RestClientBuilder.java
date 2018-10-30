package com.beyondsot.latte.net;


import android.content.Context;

import com.beyondsot.latte.net.callback.IError;
import com.beyondsot.latte.net.callback.IFailure;
import com.beyondsot.latte.net.callback.IRequest;
import com.beyondsot.latte.net.callback.ISuccess;
import com.beyondsot.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 请求的构建者
 */
public class RestClientBuilder {
    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>(); //请求参数
    private String mUrl = null; //路径
    private IRequest mIRequest = null; //请求状态
    private ISuccess mISuccess = null; //请求成功
    private IFailure mIFailure = null;//请求失败
    private IError mIError = null; //请求错误
    private RequestBody mBody = null; //请求体
    private Context mContext = null; //上下文
    private LoaderStyle mLoaderStyle = null;// 加载框样式
    private File mFile = null; //文件
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {  //存放的目錄
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) { //後綴名稱
        this.mExtension = extension;
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    //自定义
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    //默认
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.LineScaleIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                mDownloadDir, mExtension, mName,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody, mFile, mContext,
                mLoaderStyle);

    }
}
