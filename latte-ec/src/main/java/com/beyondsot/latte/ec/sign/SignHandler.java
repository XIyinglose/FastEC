package com.beyondsot.latte.ec.sign;

import com.beyondsot.latte.app.AccountManager;
import com.beyondsot.latte.ec.database.DatabaseManager;
import com.beyondsot.latte.ec.database.UserProfile;
import com.beyondsot.latte.ec.sign.bean.SinlnDelegateBean;
import com.beyondsot.latte.util.fastjson.FastJsonUtil;

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        SinlnDelegateBean sinlnDelegateBean = FastJsonUtil.getObject(response, SinlnDelegateBean.class);
        if (sinlnDelegateBean != null) {
            if (sinlnDelegateBean.getCode() == 0) {
                if (sinlnDelegateBean.getData() != null) {
                    long userId = sinlnDelegateBean.getData().getUserId();
                    String name = sinlnDelegateBean.getData().getName();
                    String avatar = sinlnDelegateBean.getData().getAvatar();
                    String gender = sinlnDelegateBean.getData().getGender();
                    String address = sinlnDelegateBean.getData().getAddress();
                    //已经注册并登录成功了
                    AccountManager.setSignState(true);
                    signListener.onSignInSuccess();
                } else {
                    if (sinlnDelegateBean.getMessage() != null) {
                        signListener.onSignInError(sinlnDelegateBean.getMessage());
                    } else {
                        signListener.onSignInError("登錄失敗！");
                    }

                }

            } else {
                signListener.onSignInError("登錄失敗！");
            }

        }


    }

    public static void onSignUp(String response, ISignListener signListener) {
        SinlnDelegateBean sinlnDelegateBean = FastJsonUtil.getObject(response, SinlnDelegateBean.class);
        if (sinlnDelegateBean != null) {
            if (sinlnDelegateBean.getCode() == 0) {
                if (sinlnDelegateBean.getData() != null) {
                    final long userId = sinlnDelegateBean.getData().getUserId();
                    final String name = sinlnDelegateBean.getData().getName();
                    final String avatar = sinlnDelegateBean.getData().getAvatar();
                    final String gender = sinlnDelegateBean.getData().getGender();
                    final String address = sinlnDelegateBean.getData().getAddress();
                    //保存个人信息
                    final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
                    DatabaseManager.getInstance().getDao().insert(profile); //保存数据库
                    //已经注册并登录成功了
                    AccountManager.setSignState(true);
                    signListener.onSignInSuccess();
                } else {
                    if (sinlnDelegateBean.getMessage() != null) {
                        signListener.onSignInError(sinlnDelegateBean.getMessage());
                    } else {
                        signListener.onSignInError("注册失敗！");
                    }

                }

            } else {
                signListener.onSignInError("注册失敗！");
            }

        }
        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }


}
