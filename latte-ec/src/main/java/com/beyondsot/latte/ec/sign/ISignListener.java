package com.beyondsot.latte.ec.sign;


/**
 * 登錄成功跟登錄失敗的接口
 */
public interface ISignListener {

    void onSignInSuccess();

    void onSignInError(String msg);

    void onSignUpSuccess();

    void onSignUpError(String msg);
}
