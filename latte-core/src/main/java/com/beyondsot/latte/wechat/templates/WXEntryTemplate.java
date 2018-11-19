package com.beyondsot.latte.wechat.templates;

import com.beyondsot.latte.wechat.base.BaseWXEntryActivity;
import com.beyondsot.latte.wechat.base.LatteWeChat;

/**
 * 微信成功返回， 這個頁面需要做一個優化處理， 首先需要把這個頁面做一個透明的，處理完了，馬上就finish 掉
 */
public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0); // 不需要動畫效果
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSingnInSuccess(userInfo);

    }
}
