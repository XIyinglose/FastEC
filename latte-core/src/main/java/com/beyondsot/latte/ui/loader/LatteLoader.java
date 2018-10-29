package com.beyondsot.latte.ui.loader;


import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.beyondsot.latte.util.dimen.DimenUtil;
import com.beyondsot.latte_core.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


/**
 * 加载框的管理类
 */
public class LatteLoader {
    //样式大小比例
    private static final int LOADER_SIZE_SCALE = 8;
    //样式的偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    //存储 dialog 方便管理
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    //默认样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();
    /**
     *  显示加载框
     * @param context 上下文
     * @param type 加载框的类型
     */
    public static  void showLoading(Context context,String type){
        // 创建加载框来承载起来, 创建一个dialog 来是灰色 透明
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //拿到我的封装好的IndicatorView
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        //设置上去
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();

    }

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     *关闭loading
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

}
