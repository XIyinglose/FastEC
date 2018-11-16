package com.beyondsot.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.beyondsot.latte.app.AccountManager;
import com.beyondsot.latte.app.IUserChecker;
import com.beyondsot.latte.delegates.LatteDelegate;
import com.beyondsot.latte.util.storage.LattePreference;
import com.beyondsot.latte.util.timer.BaseTimerTask;
import com.beyondsot.latte.util.timer.ITimerListener;
import com.beyondsot.latte_ec.R;
import com.beyondsot.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer = null;
    private Timer mTimer;
    private int mCount = 5;
    private TLauncherListener mTLauncherListener = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TLauncherListener) {
            mTLauncherListener = (TLauncherListener) activity;
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }


    @OnClick(R2.id.tv_launcher_timer)
    public void onViewClicked() {
        onClickTimerView();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvLauncherTimer != null) {
                    tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    /**
     * 點擊倒計時
     */
    private void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    /*
     *  是否顯示滾動廣告欄
     */
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            //啟動
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //檢查用戶是否登錄app
            AccountManager.checkAccount(new IUserChecker() {
                // 已经有用户信息的
                @Override
                public void onSignIn() {
                    if (mTLauncherListener != null) {
                        mTLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                //么有用户信息
                @Override
                public void onNotSignIn() {
                    if (mTLauncherListener != null) {
                        mTLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }
}
