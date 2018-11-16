package com.beyondsot.latte.app;

import com.beyondsot.latte.util.storage.LattePreference;

/**
 * 管理用戶信息
 */
public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用戶登錄狀態，登錄后調用

    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    //是否是登錄中
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

}
