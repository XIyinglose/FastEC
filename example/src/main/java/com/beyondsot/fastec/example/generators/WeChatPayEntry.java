package com.beyondsot.fastec.example.generators;


import com.beyondsot.annotations.PayEntryGenerator;
import com.beyondsot.latte.wechat.templates.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.beyondsot.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
