package org.arpnetwork.eoswallet.ui.wallet.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.blockchain.cypto.ec.EosPrivateKey;
import org.arpnetwork.eoswallet.blockchain.wallet.EosWalletManager;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.ui.wallet.CheckPolicyView;
import org.arpnetwork.eoswallet.ui.wallet.PasswordView;
import org.arpnetwork.eoswallet.ui.wallet.StepIndicatorView;
import org.arpnetwork.eoswallet.util.PreferenceManager;
import org.arpnetwork.eoswallet.util.UIHelper;


import java.io.IOException;

public class CreateWalletFragment extends BaseFragment {

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
        StepIndicatorView stepView = (StepIndicatorView) findViewById(R.id.step_view);
        stepView.setTotalAndIndicate(3, 1);

        mPasswordView = (PasswordView) findViewById(R.id.v_password);
        mCheckPolicyView = (CheckPolicyView) findViewById(R.id.v_policy);

        findViewById(R.id.btn_create_wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckPolicyView.isCheck() && mPasswordView.getPassword() != null) {
                    createWallet();
                }
            }
        });
    }

    private void createWallet() {
        EosWalletManager walletManager = EosWalletManager.getInstance();
        if (!walletManager.walletExists(Constant.WALLET_NAME)) {
            String password = null;
            try {
                password = walletManager.create(Constant.WALLET_NAME);
            } catch (IOException e) {
                UIHelper.showToast(getContext(), getResources().getString(R.string.create_wallet_failed) + e.getMessage());
            }
            debugLog("create wallet, wallet name = " + Constant.WALLET_NAME + ", password = " + password);
            PreferenceManager.getInstance().putString(Constant.WALLET_PASSWORD, password);
        } else {
            walletManager.open(Constant.WALLET_NAME);
        }
        if (walletManager.isLocked(Constant.WALLET_NAME)) {
            walletManager.unlock(Constant.WALLET_NAME, PreferenceManager.getInstance().getString(Constant.WALLET_PASSWORD));
        }
        // TODO：是否处理钱包内已有密钥情况
        String pvtKey = createPrivateKey();
        debugLog("create wallet, private key = " + pvtKey);
        walletManager.importKey(Constant.WALLET_NAME, pvtKey);
        backupPrivateKey(pvtKey);
    }

    private String createPrivateKey() {
        EosPrivateKey pvtKey = new EosPrivateKey();
        return pvtKey.toWif();
    }

    private void backupPrivateKey(String pvtKey) {
        BackupPrivateKeyActivity.launch(getActivity(), pvtKey, 0);
    }

}
