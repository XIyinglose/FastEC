package com.beyondsot.fastec.example;

import android.app.Application;

import com.beyondsot.latte.app.Latte;
import com.beyondsot.latte.ec.database.DatabaseManager;
import com.beyondsot.latte.ec.icon.FontEcModule;
import com.beyondsot.latte.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .WithIcon(new FontAwesomeModule()) //添加字体图标
                .WithIcon(new FontEcModule()) //自定义字体的图标
                .withApiHost("http://192.168.31.80:20002/api/")
                .withLoaderDelayed(1000) //加载框的停留时间
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("你的微信AppKey")
                .withWeChatAppSecret("你的微信AppSecret")
                .configure();

        //数据库的初始化
        DatabaseManager.getInstance().init(this);

        initStetho();
    }
    private void  initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
