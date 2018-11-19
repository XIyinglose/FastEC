package com.beyondsot.fastec.example.generators;


import com.beyondsot.annotations.EntryGenerator;
import com.beyondsot.latte.wechat.templates.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.beyondsot.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
