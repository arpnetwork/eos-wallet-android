package org.arpnetwork.eoswallet.ui.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.base.BaseUrl;
import org.arpnetwork.eoswallet.blockchain.EosDataManger;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.net.HttpUtils;
import org.arpnetwork.eoswallet.net.callbck.JsonCallback;
import org.arpnetwork.eoswallet.ui.ReceiptActivity;
import org.arpnetwork.eoswallet.ui.assets.AssetsDetailActivity;
import org.arpnetwork.eoswallet.ui.resource.ResourceTransActivity;
import org.arpnetwork.eoswallet.ui.transfer.TransferActivity;
import com.google.gson.JsonArray;
import com.lzy.okgo.model.Response;

import java.util.Locale;

public class WalletFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = WalletFragment.class.getSimpleName();

    private TextView mTotalAmountView;
    private TextView mTotalCNYView;
    private TextView mEOSPriceView;
    private TextView mEOSAmountView;
    private TextView mEOSCNYView;
    private TextView mBHKDPriceView;
    private TextView mBHKDAmountView;
    private TextView mBHKDCNYView;
    private Button mAccountButton;
    private ImageButton mShowPasswordButton;

    private float mEOSPrice;
    private float mEOSBalance;
    private float mBHKDPrice;
    private float mBHKDBalance;

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
        getEOSPrice();
        getBHKDPrice();
        getEOSBalance("unsupporteda");
        getBHKDBalance("unsupporteda");
    }

    private void initViews() {
        mTotalAmountView = (TextView) findViewById(R.id.tv_total_amount);
        mTotalCNYView = (TextView) findViewById(R.id.tv_cny);
        mEOSPriceView = (TextView) findViewById(R.id.tv_eos_price);
        mEOSAmountView = (TextView) findViewById(R.id.tv_eos_amount);
        mEOSCNYView = (TextView) findViewById(R.id.tv_eos_cny);
        mBHKDPriceView = (TextView) findViewById(R.id.tv_bhkd_price);
        mBHKDAmountView = (TextView) findViewById(R.id.tv_bhkd_amount);
        mBHKDCNYView = (TextView) findViewById(R.id.tv_bhkd_cny);

        mAccountButton = (Button) findViewById(R.id.btn_account);
        mShowPasswordButton = (ImageButton) findViewById(R.id.ib_show_password);
        mAccountButton.setOnClickListener(this);
        mShowPasswordButton.setOnClickListener(this);

        findViewById(R.id.layout_receipt).setOnClickListener(this);
        findViewById(R.id.layout_transfer).setOnClickListener(this);
        findViewById(R.id.layout_eos).setOnClickListener(this);
        findViewById(R.id.layout_bhkd).setOnClickListener(this);
        findViewById(R.id.btn_resource).setOnClickListener(this);
    }

    private void updateTotalAmount() {
        float totalAmount = mEOSBalance + mBHKDBalance;
        mTotalAmountView.setText(String.format(Locale.US, "%.4f", totalAmount));
        updateTotalCNY();
    }

    private void updateTotalCNY() {
        float totalCNY = mEOSPrice * mEOSBalance + mBHKDPrice * mBHKDBalance;
        mTotalCNYView.setText(String.format(Locale.US, "≈%.2f CNY", totalCNY));
    }

    private void updateEOSPrice() {
        updateTotalCNY();
        mEOSPriceView.setText(String.format(Locale.US, "¥%.2f", mEOSPrice));
        mEOSCNYView.setText(String.format(Locale.US, "≈ ¥%.2f", mEOSPrice * mEOSBalance));
    }

    private void updateEOSBalance() {
        updateTotalAmount();
        mEOSAmountView.setText(String.format(Locale.US, "%.4f", mEOSBalance));
        mEOSCNYView.setText(String.format(Locale.US, "≈ ¥%.2f", mEOSPrice * mEOSBalance));
    }

    private void updateBHKDPrice() {
        updateTotalCNY();
        mBHKDPriceView.setText(String.format(Locale.US, "¥%.2f", mBHKDPrice));
        mBHKDCNYView.setText(String.format(Locale.US, "≈ ¥%.2f", mBHKDPrice * mBHKDBalance));
    }

    private void updateBHKDBalance() {
        updateTotalAmount();
        mBHKDAmountView.setText(String.format(Locale.US, "%.4f", mBHKDBalance));
        mBHKDCNYView.setText(String.format(Locale.US, "≈ ¥%.2f", mBHKDPrice * mBHKDBalance));
    }

    private void getEOSPrice() {
        HttpUtils.getRequets(BaseUrl.HTTP_GET_EOS_PRICE, "", null, new JsonCallback<Float>() {
            @Override
            public void onSuccess(Response<Float> response) {
                mEOSPrice = response.body();
                updateEOSPrice();
            }

            @Override
            public void onError(Response<Float> response) {
                super.onError(response);
            }
        });
    }

    private void getBHKDPrice() {
        HttpUtils.getRequets(BaseUrl.HTTP_GET_BHKD_PRICE, "", null, new JsonCallback<Float>() {
            @Override
            public void onSuccess(Response<Float> response) {
                mBHKDPrice = response.body();
                updateBHKDPrice();
            }

            @Override
            public void onError(Response<Float> response) {
                super.onError(response);
            }
        });
    }

    private void getEOSBalance(String account) {
        EosDataManger.getInstance().getCurrencyBalance(Constant.EOSIO_TOKEN_CONTRACT, account, Constant.EOS_SYMBOL_STRING, new JsonCallback<JsonArray>() {
            @Override
            public void onSuccess(Response<JsonArray> response) {
                JsonArray array = response.body();
                if (array != null && array.size() > 0) {
                    String eosRes = array.get(0).getAsString();
                    mEOSBalance = Float.valueOf(eosRes.split(" ")[0]);
                }
                updateEOSBalance();
            }
        });
    }

    private void getBHKDBalance(String account) {
        EosDataManger.getInstance().getCurrencyBalance(Constant.BHKD_TOKEN_CONTRACT, account, Constant.BHKD_SYMBOL_STRING, new JsonCallback<JsonArray>() {
            @Override
            public void onSuccess(Response<JsonArray> response) {
                JsonArray array = response.body();
                if (array != null && array.size() > 0) {
                    String bhkdRes = array.get(0).getAsString();
                    mBHKDBalance = Float.valueOf(bhkdRes.split(" ")[0]);
                }
                updateBHKDBalance();
            }
        });
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
                startActivity(AssetsDetailActivity.class);
                break;
            case R.id.layout_bhkd:
                break;
            case R.id.btn_resource:
                startActivity(ResourceTransActivity.class);
                break;
            default:
                break;
        }
    }
}
