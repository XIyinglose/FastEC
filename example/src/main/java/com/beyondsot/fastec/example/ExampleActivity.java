package com.beyondsot.fastec.example;

import com.beyondsot.latte.Delegates.LatteDelegate;
import com.beyondsot.latte.activities.ProxyActivity;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
