package org.arpnetwork.eoswallet.ui.wallet.create;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.ui.wallet.CheckPolicyView;
import org.arpnetwork.eoswallet.ui.wallet.PasswordView;
import org.arpnetwork.eoswallet.util.UIHelper;

public class CreateWalletFragment extends BaseFragment {
    EditText mAccountNameET;
    PasswordView mPasswordView;
    CheckPolicyView mCheckPolicyView;

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
        mPasswordView = (PasswordView) findViewById(R.id.v_password);
        mCheckPolicyView = (CheckPolicyView) findViewById(R.id.v_policy);

        findViewById(R.id.btn_create_wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mAccountNameET.getText().toString())) {
                    UIHelper.showToast(getContext(), R.string.enter_account_name);
                } else if (mCheckPolicyView.isCheck() && mPasswordView.getPassword() != null) {
                    // TODO:创建钱包

                    backupPrivateKey();
                }
            }
        });
    }

    private void backupPrivateKey() {
        // TODO:生成新私钥
        BackupPrivateKeyActivity.launch(getActivity(), "123", 0);
    }

}
