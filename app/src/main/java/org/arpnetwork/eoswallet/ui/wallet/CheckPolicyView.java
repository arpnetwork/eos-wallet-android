package org.arpnetwork.eoswallet.ui.wallet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.util.UIHelper;

public class CheckPolicyView extends LinearLayout {
    CheckBox mPolicyCB;

    public CheckPolicyView(Context context) {
        this(context, null);
    }

    public CheckPolicyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckPolicyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.item_policy, this, true);
        initView();
    }

    public boolean isCheck() {
        boolean isChecked = mPolicyCB.isChecked();
        if (!isChecked) UIHelper.showToast(getContext(), R.string.check_policy);
        return mPolicyCB.isChecked();
    }

    private void initView() {
        mPolicyCB = findViewById(R.id.cb_policy);
        findViewById(R.id.btn_policy).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 显示隐私条款
            }
        });
    }
}
