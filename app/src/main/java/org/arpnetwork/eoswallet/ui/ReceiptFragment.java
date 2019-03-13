package org.arpnetwork.eoswallet.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.util.QRCodeUtil;
import org.arpnetwork.eoswallet.util.UIHelper;

import org.arpnetwork.eoswallet.R;

public class ReceiptFragment extends BaseFragment {
    private String mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.receipt);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_receipt, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        getBaseActivity().hideTitleDivider();
    }

    private void initViews() {
        TextView receiptTitle = (TextView) findViewById(R.id.tv_qrcode_title);
        receiptTitle.setText(R.string.receipt_title);
        TextView receiptTail = (TextView) findViewById(R.id.tv_qrcode_tail);
        receiptTail.setText(R.string.receipt_tail);

        // TODO: get Wallet addr to create QRCode.
        mContent = "324234234235FT34534HJ43GJH34G4GKJ4HK3";

        final ImageView qrcodeImageView = (ImageView) findViewById(R.id.iv_qrcode);
        Bitmap bitmap = QRCodeUtil.createQRCodeBitmap(mContent, UIHelper.dip2px(getActivity(), 217));
        qrcodeImageView.setImageBitmap(bitmap);

        TextView contentView = (TextView) findViewById(R.id.tv_qrcode_content);
        contentView.setText(mContent);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO:
            }
        });

        findViewById(R.id.btn_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO:
            }
        });
    }

}
