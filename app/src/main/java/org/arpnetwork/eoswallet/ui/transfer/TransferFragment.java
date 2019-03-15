package org.arpnetwork.eoswallet.ui.transfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.ScanNeededFragment;

public class TransferFragment extends ScanNeededFragment implements View.OnClickListener {
    private Button mTypeButton;
    private EditText mEditReceiptAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle(R.string.transfer);
    }

    @Override
    protected void setScanResult(String result) {
        super.setScanResult(result);
        mEditReceiptAccount.setText(result);
    }

    private void initViews() {
        mEditReceiptAccount = (EditText) findViewById(R.id.et_receipt_account);

        mTypeButton = (Button) findViewById(R.id.btn_type);
        mTypeButton.setOnClickListener(this);

        findViewById(R.id.ib_scan).setOnClickListener(this);
        findViewById(R.id.btn_all).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_scan:
                checkPermission();
                break;
            case R.id.btn_type:
                break;
            case R.id.btn_all:
                break;
            case R.id.btn_confirm:
                break;
            default:
                break;
        }
    }
}
