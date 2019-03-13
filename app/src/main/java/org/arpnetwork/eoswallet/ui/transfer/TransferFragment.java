package org.arpnetwork.eoswallet.ui.transfer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.qr.QRCodeScannerActivity;
import org.arpnetwork.eoswallet.util.PreferenceManager;

public class TransferFragment extends BaseFragment implements View.OnClickListener {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("TransferFragment", "onRequestPermissionsResult");
        switch (requestCode) {
            case Constant.PERMISSION_REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scanPrivateKey();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constant.QRCODE_REQUEST && data != null) {
            String result = data.getExtras().getString(Constant.ACTIVITY_RESULT_KEY_ADDRESS);
            mEditReceiptAccount.setText(result);
        }
    }

    private void initViews() {
        mEditReceiptAccount = (EditText) findViewById(R.id.et_receipt_account);

        mTypeButton = (Button) findViewById(R.id.btn_type);
        mTypeButton.setOnClickListener(this);

        findViewById(R.id.ib_scan).setOnClickListener(this);
        findViewById(R.id.btn_all).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                boolean should = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA);
                boolean hasRequest = PreferenceManager.getInstance().getBoolean(Constant.KEY_REQUEST_PERMISSIONS);
                if (should || !hasRequest) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            Constant.PERMISSION_REQUEST_CODE_CAMERA);
                    PreferenceManager.getInstance().putBoolean(Constant.KEY_REQUEST_PERMISSIONS, true);
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setMessage(getString(R.string.required_camera_permission))
                            .setPositiveButton(R.string.ok, null)
                            .create()
                            .show();
                }
                return;
            }
        }
        scanPrivateKey();
    }

    private void scanPrivateKey() {
        Intent intent = new Intent(getActivity(), QRCodeScannerActivity.class);
        startActivityForResult(intent, Constant.QRCODE_REQUEST);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_scan:
                checkPermissions();
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
