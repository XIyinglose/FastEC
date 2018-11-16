package com.beyondsot.latte.ec.launcher;

public interface TLauncherListener {

     //是登录成功的finish, 还是登录失败的finish
    void onLauncherFinish(OnLauncherFinishTag tag);

}
