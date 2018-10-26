package com.beyondsot.latte.net;


import com.beyondsot.latte.net.callback.IError;
import com.beyondsot.latte.net.callback.IFailure;
import com.beyondsot.latte.net.callback.IRequest;
import com.beyondsot.latte.net.callback.ISuccess;

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

    public final RestClient build() {

        return new RestClient(mUrl, PARAMS,
                mIRequest, mISuccess,
                mIFailure, mIError, mBody);
    }
}
