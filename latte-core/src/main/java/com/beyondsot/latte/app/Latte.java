package com.beyondsot.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * 对外工具类的所以使用一些静态方法
 */
public class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();

    }

    private static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();

    }
}
