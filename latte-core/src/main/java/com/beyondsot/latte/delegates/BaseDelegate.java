package com.beyondsot.latte.delegates;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyondsot.latte.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseDelegate extends SwipeBackFragment {

    //设置布局
    public abstract Object setLayout();
    //butterknife
    private Unbinder mUnbinder = null;
    //绑定View
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);
    private View mRootView = null;

    public <T extends View> T $(@IdRes int viewId) {
        if (mRootView != null) {
            return mRootView.findViewById(viewId);
        }
        throw new NullPointerException("rootView is null");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        if (rootView != null) {
            mRootView = rootView;
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
