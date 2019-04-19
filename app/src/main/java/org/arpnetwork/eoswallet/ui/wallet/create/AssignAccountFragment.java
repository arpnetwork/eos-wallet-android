package org.arpnetwork.eoswallet.ui.wallet.create;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.ui.wallet.StepIndicatorView;
import org.arpnetwork.eoswallet.util.UIHelper;

public class AssignAccountFragment extends BaseFragment {
    private String mAccountName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_assign_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mAccountName = bundle.getString(Constant.ACCOUNT_NAME);
        }
        initViews();
    }

    private void initViews() {
        StepIndicatorView stepView = (StepIndicatorView) findViewById(R.id.step_view);
        stepView.setTotalAndIndicate(3, 3);
        ((TextView) findViewById(R.id.tv_account_name)).setText(mAccountName);
        setRefreshBtn();
        UIHelper.setIndentationText(getContext(), (TextView) findViewById(R.id.tv_detail), getString(R.string.assign_account_tip));

        findViewById(R.id.btn_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cmb = (ClipboardManager) getActivity()
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(mAccountName);
            }
        });

        findViewById(R.id.btn_active).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:激活账户
            }
        });
    }

    private void setRefreshBtn() {
        final TextView refreshText = (TextView) findViewById(R.id.tv_refresh);
        final ProgressBar refreshProgress = (ProgressBar) findViewById(R.id.pb_refresh);
        final FrameLayout refreshBtn = (FrameLayout) findViewById(R.id.btn_refresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshText.setVisibility(View.GONE);
                refreshProgress.setVisibility(View.VISIBLE);
                refreshBtn.setEnabled(false);

                // TODO:获取余额

                //TODO：获取成功后
//                refreshText.setVisibility(View.VISIBLE);
//                refreshProgress.setVisibility(View.GONE);
//                refreshBtn.setEnabled(true);
            }
        });
    }
}
