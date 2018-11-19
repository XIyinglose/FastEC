package com.beyondsot.fastec.example.generators;


import com.beyondsot.annotations.AppRegisterGenerator;
import com.beyondsot.latte.wechat.templates.AppRegisterTemplate;

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.beyondsot.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
