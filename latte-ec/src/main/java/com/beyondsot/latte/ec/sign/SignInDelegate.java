package com.beyondsot.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.beyondsot.latte.net.RestClient;
import com.beyondsot.latte.net.callback.ISuccess;
import com.beyondsot.latte.util.log.LatteLogger;
import com.beyondsot.latte_ec.R;
import com.beyondsot.latte.delegates.LatteDelegate;

public class SignInDelegate extends LatteDelegate implements View.OnClickListener {
    private TextInputEditText mEmail = null;
    private TextInputEditText mPassword = null;
    private ISignListener mISignListener = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mEmail = $(R.id.edit_sign_in_email);
        mPassword = $(R.id.edit_sign_in_password);
        $(R.id.btn_sign_in).setOnClickListener(this);
        $(R.id.tv_link_sign_up).setOnClickListener(this);
        $(R.id.icon_sign_in_wechat).setOnClickListener(this);
    }

    /**
     *  微信登錄
     */
    private void onClickWeChat() {

    }

    /**
     * 註冊
     */
    private void onClickLink() {
        start(new SignUpDelegate(),SINGLETASK);
    }

    /**
     *  登錄
     */
    private void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock/data/user_profile.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);

                        }
                    })
                    .build()
                    .post();
        }
    }

    /**
     *  輸入格式檢查
     * @return
     */
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_in) {  //登錄
            onClickSignIn();
        } else if (i == R.id.tv_link_sign_up) {  // 註冊
            onClickLink();
        } else if (i == R.id.icon_sign_in_wechat) { // 微信登錄
            onClickWeChat();
        }

    }
}
