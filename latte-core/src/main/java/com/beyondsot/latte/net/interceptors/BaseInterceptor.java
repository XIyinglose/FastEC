package com.beyondsot.latte.net.interceptors;

import java.util.LinkedHashMap;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;


/**
 * 攔截器的封裝
 */
public abstract class BaseInterceptor implements Interceptor {

    /**
     *  獲取 URL 的參數
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameters (Chain chain){
        final HttpUrl url = chain.request().url(); //獲取URL
        int size = url.querySize(); //請求參數的個數
        final LinkedHashMap<String, String> params = new LinkedHashMap<>(); //保存在這個集合
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 通過K值來獲取 value
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 獲取返回的參數，LinkedHashMap 來保證順序
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = 0;
        if (formBody != null) {
            size = formBody.size();
        }
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     * h獲取返回值的k 來獲取值
     * @param chain
     * @param key
     * @return
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
