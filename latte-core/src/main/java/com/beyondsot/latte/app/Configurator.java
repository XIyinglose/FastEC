package com.beyondsot.latte.app;

import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 进行一些配置文件的存取跟获取的
 */
public final class Configurator {
    //配置的文件的存取
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //存储 图标文字的容器
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //攔截器的容器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);  // 配置已经开始，但是没有配置完成
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //静态内部内，单例模式的初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }

    // 是指配置已经完成
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);

    }

    //配置全局的URL路径
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    //初始化文字图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }

        }
    }

    //自定义文字图标
    public final Configurator WithIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //检查配置有没有检查完成
    private void checkConfiguration() {
        final boolean isReaby = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReaby) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //获取配置
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
