package com.beyondsot.latte.net.callback;


/**
 * 请求错误跟异常的回调
 */
public interface IError {

    void onError(int code, String msg);
}
