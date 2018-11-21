package com.beyondsot.latte.ec.main.shopcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.beyondsot.latte.delegates.bottom.BottomItemDelegate;
import com.beyondsot.latte_ec.R;

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcar;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
