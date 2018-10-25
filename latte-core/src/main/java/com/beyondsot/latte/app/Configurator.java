package com.beyondsot.latte.app;

import java.util.WeakHashMap;

/**
 * 进行一些配置文件的存取跟获取的
 */
public final class Configurator {
    //配置的文件的存取
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);  // 配置已经开始，但是没有配置完成
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {

        return LATTE_CONFIGS;
    }

    //静态内部内，单例模式的初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }

    // 是指配置已经完成
    public final void configure() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);

    }

    //配置全局的URL路径
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    //检查配置有没有检查完成
    private void  checkConfiguration(){
        final  boolean isReaby = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReaby){
            throw  new RuntimeException("Configuration is not ready,call configure");
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
