package com.beyondsot.latte.ec.main;

import android.graphics.Color;

import com.beyondsot.latte.delegates.bottom.BaseBottomDelegate;
import com.beyondsot.latte.delegates.bottom.BottomItemDelegate;
import com.beyondsot.latte.delegates.bottom.BottomTabBean;
import com.beyondsot.latte.delegates.bottom.ItemBuilder;
import com.beyondsot.latte.ec.main.discover.DiscoverDelegate;
import com.beyondsot.latte.ec.main.index.IndexDelegate;
import com.beyondsot.latte.ec.main.personal.PersonalDelegate;
import com.beyondsot.latte.ec.main.shopcart.ShopCartDelegate;
import com.beyondsot.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate  extends BaseBottomDelegate{

    /**
     *  設置底部的數據
     * @param builder
     * @return
     */
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    /**
     *  默認選中
     * @return
     */
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    /***
     *  點擊后的顏色變化
     * @return
     */
    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
