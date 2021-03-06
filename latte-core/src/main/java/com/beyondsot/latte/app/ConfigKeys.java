package com.beyondsot.latte.app;


/**
 * 枚举类
 * 是整个应用的唯一的单例，只能被初始化一次。线程安全的懒汉式。
 */
public enum ConfigKeys {
    API_HOST,  //网络请求的域名
    APPLICATION_CONTEXT, //全局上下文
    CONFIG_READY, //控制配置是否初始化完成
    ICON, //其他的字体配置
    LOADER_DELAYED, //加载框
    INTERCEPTOR, //攔截器
    WE_CHAT_APP_ID, //微信appID
    WE_CHAT_APP_SECRET, //微信
    ACTIVITY,//activity
    HANDLER,
    JAVASCRIPT_INTERFACE,
    WEB_HOST
}
