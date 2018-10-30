package com.beyondsot.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.beyondsot.latte.Delegates.LatteDelegate;
import com.beyondsot.latte.net.RestClient;
import com.beyondsot.latte.net.callback.IError;
import com.beyondsot.latte.net.callback.IFailure;
import com.beyondsot.latte.net.callback.ISuccess;

import javax.xml.transform.Result;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private  void  testRestClient(){
        RestClient.builder()
                .url("https://www.baidu.com/")
                .loader(getContext()) //添加加载框
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                //        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();

    }
}
