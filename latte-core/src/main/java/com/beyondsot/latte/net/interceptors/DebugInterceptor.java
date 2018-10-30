package com.beyondsot.latte.net.interceptors;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;
import android.util.Log;

import com.beyondsot.latte.util.file.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 調試攔截器
 */
public class DebugInterceptor extends  BaseInterceptor {

    private final String DEBUG_URL;  //調試URL
    private final int DEBUG_RAW_ID; //本地文件的ID

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    /**
     * 獲取返回文件
     * @param chain
     * @param json
     * @return
     */
    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200) //成功code
                .addHeader("Content-Type", "application/json") //請求頭
                .body(ResponseBody.create(MediaType.parse("application/json"), json))//請求體
                .message("OK") //請求返回
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1) //請求協議
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId); // 獲取raw 的文件
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();  //攔截 URL
        Log.i("+++++", "intercept: ---->"+url);
        if (url.contains(DEBUG_URL)) {  //包含
            return debugResponse(chain, DEBUG_RAW_ID);  //過濾的文件
        }
        return chain.proceed(chain.request());
    }
}
