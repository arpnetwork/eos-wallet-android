package org.arpnetwork.eoswallet.ui.wallet.create;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.util.UIHelper;
import org.arpnetwork.eoswallet.util.Util;

public class CreateWalletFragment extends BaseFragment {
    private static final String TAG = "CreateWalletFragment";
    EditText mAccountNameET;
    EditText mPasswordET;
    EditText mConfirmPWET;
    TextView mPWTypeTV;
    ImageView mPWTypeIV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_create_wallet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mAccountNameET = (EditText) findViewById(R.id.et_account_name);
        mPasswordET = (EditText) findViewById(R.id.et_password);
        mConfirmPWET = (EditText) findViewById(R.id.et_confirm_password);
        mPWTypeTV = (TextView) findViewById(R.id.tv_password_type);
        mPWTypeIV = (ImageView) findViewById(R.id.iv_password_type);

        final CheckBox showPWBtn = (CheckBox) findViewById(R.id.cb_password_visible);
        showPWBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setPasswordVisible(b);
            }
        });

        findViewById(R.id.btn_licence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:显示隐私服务条款
            }
        });

        findViewById(R.id.btn_create_wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = mPasswordET.getText().toString();
                if (TextUtils.isEmpty(mAccountNameET.getText().toString())) {
                    UIHelper.showToast(getContext(), R.string.enter_account_name);
                } else if (password.equals(mConfirmPWET.getText().toString())) {
                    // TODO: create password
                } else {
                    UIHelper.showToast(getContext(), R.string.password_confirm_failed);
                }
            }
        });

        mPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "onTextChanged: charSequence" + charSequence);
                checkPasswordType(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setPasswordVisible(boolean visible) {
        mPasswordET.setTransformationMethod(visible ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        mConfirmPWET.setTransformationMethod(visible ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
    }

    // TODO: 设置密码强度UI
    private void checkPasswordType(CharSequence charSequence) {
        int passwordType = Util.evaluatePassword(charSequence.toString());
        mPWTypeIV.setVisibility(passwordType > 0 ? View.VISIBLE : View.GONE);
        mPWTypeTV.setVisibility(passwordType > 0 ? View.VISIBLE : View.GONE);
        switch (passwordType) {
            case -1:
                UIHelper.showToast(getContext(), R.string.illegal_password);
                break;

            case 0:
                UIHelper.showToast(getContext(), R.string.enter_password);
                break;

            case 1:
                mPWTypeTV.setTextColor(getResources().getColor(R.color.password_type_1_text));
                mPWTypeTV.setText(R.string.password_type_1);
                break;

            case 2:
                mPWTypeTV.setTextColor(getResources().getColor(R.color.password_type_2_text));
                mPWTypeTV.setText(R.string.password_type_2);
                break;

            case 3:
                mPWTypeTV.setTextColor(getResources().getColor(R.color.password_type_3_text));
                mPWTypeTV.setText(R.string.password_type_3);
                break;
        }
    }
}
