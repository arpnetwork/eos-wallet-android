package org.arpnetwork.eoswallet.ui.transaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.util.QRCodeUtil;
import org.arpnetwork.eoswallet.util.ToastUtil;
import org.arpnetwork.eoswallet.util.UIHelper;
import org.arpnetwork.eoswallet.util.Util;

public class TransactionDetailFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = TransactionDetailFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_details, container, false);
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
        setTitle(R.string.transaction_details);
    }

    private void initViews() {
        ImageView qrcodeImageView = (ImageView) findViewById(R.id.iv_qrcode);
        Bitmap bitmap = QRCodeUtil.createQRCodeBitmap("https://www.baidu.com", UIHelper.dip2px(getActivity(), 84));
        qrcodeImageView.setImageBitmap(bitmap);

        findViewById(R.id.btn_query_details).setOnClickListener(this);
        findViewById(R.id.btn_payer).setOnClickListener(this);
        findViewById(R.id.btn_payee).setOnClickListener(this);
        findViewById(R.id.btn_transaction_no).setOnClickListener(this);
    }

    private void browse() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri contentUrl = Uri.parse("https://www.baidu.com");
        intent.setData(contentUrl);
        startActivity(intent);
    }

    private void copy(View v) {
        Util.copyToClipboard(getActivity(), ((TextView) v).getText().toString());
        ToastUtil.showToast(getActivity(), R.string.copied);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query_details:
                browse();
                break;
            case R.id.btn_payer:
            case R.id.btn_payee:
            case R.id.btn_transaction_no:
                copy(v);
                break;
            default:
                break;
        }
    }
}
