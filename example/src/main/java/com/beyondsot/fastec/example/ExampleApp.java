package com.beyondsot.fastec.example;

import android.app.Application;

import com.beyondsot.latte.app.Latte;
import com.beyondsot.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .WithIcon(new FontAwesomeModule()) //添加字体图标
                .WithIcon(new FontEcModule()) //自定义字体的图标
                .configure();
    }
}
