package org.arpnetwork.eoswallet.ui.wallet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.util.UIHelper;
import org.arpnetwork.eoswallet.util.Util;

public class PasswordView extends LinearLayout {
    EditText mPasswordET;
    EditText mConfirmPWET;
    TextView mPWTypeTV;
    ImageView mPWTypeIV;

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.item_password, this, true);
        initViews();
    }

    public String getPassword() {
        if (!checkPassword()) {
            return null;
        }
        return mPasswordET.getText().toString();
    }

    private void initViews() {
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

        mPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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

    private boolean checkPassword() {
        String password = mPasswordET.getText().toString();
        if (TextUtils.isEmpty(password)) {
            UIHelper.showToast(getContext(), R.string.enter_password);
            return false;
        }
         if (!password.equals(mConfirmPWET.getText().toString())) {
            UIHelper.showToast(getContext(), R.string.password_confirm_failed);
            return false;
        }
        return true;
    }
}
