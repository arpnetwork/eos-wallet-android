package org.arpnetwork.eoswallet.ui.wallet.load;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.blockchain.cypto.ec.EosPrivateKey;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.util.UIHelper;

public class CheckAccountFragment extends BaseFragment {
    private EditText mPrivateKeyEditText;
    private TextView mPublicKeyTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_check_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPrivateKeyEditText.setText(bundle.getString(Constant.PRIVAT_KEY));
            mPrivateKeyEditText.setEnabled(false);
        }
    }

    private void initView() {
        mPrivateKeyEditText = (EditText) findViewById(R.id.et_private_key);
        Button checkPublicKeyBtn = (Button) findViewById(R.id.btn_check_public);
        mPublicKeyTV = (TextView) findViewById(R.id.tv_public_key);
        final Button accountBtn = (Button) findViewById(R.id.btn_check_account);
        final TextView accountTV = (TextView) findViewById(R.id.tv_account);

        checkPublicKeyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String publicKey = getPublicKey();
                if (publicKey == null) {
                    UIHelper.showImageToast(getContext(), R.drawable.toast_failed, R.string.key_error);
                } else {
                    mPublicKeyTV.setText(publicKey);
                    mPublicKeyTV.setVisibility(View.VISIBLE);
                    accountBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = getAccount();
                if (account == null) {
                    // TODO：提示没有账号
                } else {
                    accountTV.setText(account);
                    accountTV.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private String getPublicKey() {
        EosPrivateKey pvtKey = new EosPrivateKey(mPrivateKeyEditText.getText().toString());
        return pvtKey.getPublicKey().toString();
    }

    private String getAccount() {
        mPublicKeyTV.getText().toString();

        //TODO:根据公钥检查账号
        return "test account";
    }
}
