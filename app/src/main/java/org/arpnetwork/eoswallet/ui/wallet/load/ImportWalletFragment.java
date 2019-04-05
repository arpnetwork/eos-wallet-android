package org.arpnetwork.eoswallet.ui.wallet.load;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.ScanNeededFragment;
import org.arpnetwork.eoswallet.blockchain.cypto.ec.EosPrivateKey;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.ui.wallet.CheckPolicyView;
import org.arpnetwork.eoswallet.ui.wallet.PasswordView;
import org.arpnetwork.eoswallet.util.PreferenceManager;
import org.arpnetwork.eoswallet.util.UIHelper;

public class ImportWalletFragment extends ScanNeededFragment {
    EditText mPrivateKeyET;
    EditText mPasswordTipET;
    PasswordView mPasswordView;
    CheckPolicyView mCheckPolicyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_import_wallet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        Toolbar toolbar = getBaseActivity().getToolbar();
        ImageButton scannerIcon = new ImageButton(toolbar.getContext());
        scannerIcon.setImageResource(R.drawable.scanner_white);
        scannerIcon.setBackgroundColor(Color.TRANSPARENT);

        scannerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        Toolbar.LayoutParams lp = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.RIGHT);
        lp.rightMargin = (int) getResources().getDimension(R.dimen.horizontal_margin);
        toolbar.addView(scannerIcon, lp);

        mPrivateKeyET = (EditText) findViewById(R.id.et_private_key);
        mPasswordTipET = (EditText) findViewById(R.id.et_password_tip);
        mPasswordView = (PasswordView) findViewById(R.id.v_password);
        mCheckPolicyView = (CheckPolicyView) findViewById(R.id.v_policy);

        Button importBtn = (Button) findViewById(R.id.btn_import);
        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pvtKey = mPrivateKeyET.getText().toString();
                String pwTip = mPasswordTipET.getText().toString();
                if (TextUtils.isEmpty(pvtKey)) {
                    UIHelper.showToast(getContext(), R.string.enter_private_key);
                } else if (TextUtils.isEmpty(pwTip)) {
                    UIHelper.showToast(getContext(), R.string.enter_password_tip);
                } else if (mCheckPolicyView.isCheck() && mPasswordView.getPassword() != null) {
                    EosPrivateKey pvt = null;
                    try {
                        pvt = new EosPrivateKey(pvtKey);
                    } catch (IllegalArgumentException e) {
                        UIHelper.showToast(getContext(), R.string.illegal_private_key);
                        return;
                    }
                    if (pvt != null) {
                        PreferenceManager.getInstance().putString(Constant.PASSWORD_TIP, pwTip);
                        CheckAccountActivity.launch(getActivity(), mPrivateKeyET.getText().toString());
                    }
                }
            }
        });
    }

    @Override
    protected void setScanResult(String result) {
        super.setScanResult(result);
        mPrivateKeyET.setText(result);
    }
}
