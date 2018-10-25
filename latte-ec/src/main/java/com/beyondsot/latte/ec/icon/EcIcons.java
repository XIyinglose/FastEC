package com.beyondsot.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 自定义字体图标
 */
public enum EcIcons implements Icon {
    icon_scan('\ue602'),  //图标
    icon_ali_pay('\ue606'); //图标码
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
