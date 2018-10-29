package com.beyondsot.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 对外工具类的所以使用一些静态方法
 */
public class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
