package org.arpnetwork.eoswallet.ui.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.ui.ReceiptActivity;
import org.arpnetwork.eoswallet.ui.detail.DetailActivity;
import org.arpnetwork.eoswallet.ui.transfer.TransferActivity;

public class WalletFragment extends BaseFragment implements View.OnClickListener {
    private TextView mTotalAmountView;
    private TextView mCNYView;
    private Button mAccountButton;
    private ImageButton mShowPasswordButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle("");
        getBaseActivity().hideToolbar();
    }

    private void initViews() {
        mTotalAmountView = (TextView) findViewById(R.id.tv_total_amount);
        mCNYView = (TextView) findViewById(R.id.tv_cny);

        mAccountButton = (Button) findViewById(R.id.btn_account);
        mShowPasswordButton = (ImageButton) findViewById(R.id.ib_show_password);
        mAccountButton.setOnClickListener(this);
        mShowPasswordButton.setOnClickListener(this);

        findViewById(R.id.layout_receipt).setOnClickListener(this);
        findViewById(R.id.layout_transfer).setOnClickListener(this);
        findViewById(R.id.layout_eos).setOnClickListener(this);
        findViewById(R.id.layout_usde).setOnClickListener(this);
        findViewById(R.id.btn_resource).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_account:
                break;
            case R.id.ib_show_password:
                break;
            case R.id.layout_receipt:
                startActivity(ReceiptActivity.class);
                break;
            case R.id.layout_transfer:
                startActivity(TransferActivity.class);
                break;
            case R.id.layout_eos:
                startActivity(DetailActivity.class);
                break;
            case R.id.layout_usde:
                break;
            case R.id.btn_resource:
                break;
            default:
                break;
        }
    }
}
