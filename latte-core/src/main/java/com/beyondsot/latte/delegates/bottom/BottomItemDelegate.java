package com.beyondsot.latte.delegates.bottom;


import android.widget.Toast;

import com.beyondsot.latte.app.Latte;
import com.beyondsot.latte.delegates.LatteDelegate;
import com.beyondsot.latte_core.R;

/**
 * 这是切换fragment的基类
 */
public abstract class BottomItemDelegate extends LatteDelegate{
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    /**
     *  做双击退出应用程序
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() -TOUCH_TIME <WAIT_TIME){
            _mActivity.finish();

        }else {
            TOUCH_TIME =System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
