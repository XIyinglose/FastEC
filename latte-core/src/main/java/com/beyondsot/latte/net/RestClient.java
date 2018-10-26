package com.beyondsot.latte.net;


import com.beyondsot.latte.net.callback.IError;
import com.beyondsot.latte.net.callback.IFailure;
import com.beyondsot.latte.net.callback.IRequest;
import com.beyondsot.latte.net.callback.ISuccess;
import com.beyondsot.latte.net.callback.RequestCallbacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 網絡請求的具體實現類 ，  callback 这里面是回调接口
 */
public class RestClient {
    private final String URL;  //请求路径
    private final WeakHashMap<String, Object> PARAMS; //请求参数
    private final IRequest REQUEST; //请求的回调
    private final ISuccess SUCCESS; //请求成功的回调
    private final IFailure FAILURE; //请求失败的回调
    private final IError ERROR; //请求异常的回调
    private final RequestBody BODY; //请求体

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    //开始请求
    private void request(HttpMethod method) {
        RestService restService = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();  //请求开始
        }
        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback()); // 这是在异步线程上执行
            //  call.execute()   这是在同步， 在主线程上执行的
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    //get 请求
    public final void get() {
        request(HttpMethod.GET);
    }

    //post 请求
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    //put 请求
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    // delete 请求
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    // upload 请求
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
}
