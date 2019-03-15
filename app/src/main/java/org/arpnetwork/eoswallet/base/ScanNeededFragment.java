package org.arpnetwork.eoswallet.base;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.qr.QRCodeScannerActivity;

import static org.arpnetwork.eoswallet.misc.Constant.PERMISSION_REQUEST_CODE_CAMERA;
import static org.arpnetwork.eoswallet.misc.Constant.QRCODE_REQUEST;
import static org.arpnetwork.eoswallet.misc.Constant.SCAN_RESULT;

public class ScanNeededFragment extends BaseFragment {

    protected void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.CAMERA)) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage(getString(R.string.required_camera_permission))
                            .setPositiveButton(R.string.ok, null)
                            .create()
                            .show();
                } else {
                    ScanNeededFragment.this.requestPermissions(
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSION_REQUEST_CODE_CAMERA);
                }
                return ;
            }
        }
        scanPrivateKey();
    }

    protected void setScanResult(String result) {

    }

    private void scanPrivateKey() {
        Intent intent = new Intent(getActivity(), QRCodeScannerActivity.class);
        startActivityForResult(intent, QRCODE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE_CAMERA:
                scanPrivateKey();
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == QRCODE_REQUEST && data != null) {
            String result = data.getExtras().getString(SCAN_RESULT);
            setScanResult(result);
        }
    }
}
