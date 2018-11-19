package com.beyondsot.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.beyondsot.latte.app.Latte;
import com.beyondsot.latte.delegates.LatteDelegate;
import com.beyondsot.latte.activities.ProxyActivity;
import com.beyondsot.latte.ec.launcher.LauncherDelegate;
import com.beyondsot.latte.ec.launcher.LauncherScrollDelegate;
import com.beyondsot.latte.ec.launcher.OnLauncherFinishTag;
import com.beyondsot.latte.ec.launcher.TLauncherListener;
import com.beyondsot.latte.ec.sign.ISignListener;
import com.beyondsot.latte.ec.sign.SignInDelegate;

public class ExampleActivity extends ProxyActivity implements ISignListener,TLauncherListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    /**
     * 登錄成功
     */
    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        startWithPop( new ExampleDelegate());
    }
    /**
     * 登錄
     */
    @Override
    public void onSignInError(String msg) {
        Toast.makeText(this, "登錄失敗", Toast.LENGTH_LONG).show();
    }
    /**
     * 註冊成功
     */
    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }
    /**
     * 註冊失敗
     */
    @Override
    public void onSignUpError(String msg) {
        Toast.makeText(this, "注册失敗", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED: // 登录成功的回调
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                startWithPop( new ExampleDelegate());
                break;
            case NOT_SIGNED: // 没有登录成功的回调
                Toast.makeText(this, "启动结束，用户没有登录了", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
                default:
                    break;
        }
    }
}
