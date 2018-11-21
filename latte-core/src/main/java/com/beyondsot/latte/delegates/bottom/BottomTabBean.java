package com.beyondsot.latte.delegates.bottom;


/**
 *  这是 底部导航栏的 Bean
 */
public class BottomTabBean {

    private final CharSequence ICON; //图片
    private final CharSequence TITLE; //标题

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }


    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
