package org.arpnetwork.eoswallet.qr;

import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.widget.QRCoverView;

public class QRCodeScannerActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private QRCodeReaderView mQRCodeReaderView;
    private QRCoverView mCoverView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);
//        getSupportActionBar().hide();
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        mQRCodeReaderView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mQRCodeReaderView.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        ImageButton exitBtn = findViewById(R.id.btn_exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mQRCodeReaderView = findViewById(R.id.qr_decoder_view);
        mQRCodeReaderView.setOnQRCodeReadListener(this);
        mQRCodeReaderView.setQRDecodingEnabled(true);
        mQRCodeReaderView.setTorchEnabled(true);
        mQRCodeReaderView.setBackCamera();

        mCoverView = findViewById(R.id.qr_cover_view);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        RectF finderRect = mCoverView.getScannerRect();
        boolean isContain = true;
        for (int i = 0, length = points.length; i < length; i++) {
            if (!finderRect.contains(points[i].x, points[i].y)) {
                isContain = false;
                break;
            }
        }
        if (isContain) {
            Intent intent = new Intent();
            intent.putExtra(Constant.ACTIVITY_RESULT_KEY_ADDRESS, text);
            setResult(0, intent);
            finish();
        }
    }
}
