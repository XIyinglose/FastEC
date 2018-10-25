package com.beyondsot.fastec.example;

import android.app.Application;

import com.beyondsot.latte.app.Latte;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).configure();
    }
}
