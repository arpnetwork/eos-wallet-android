package org.arpnetwork.eoswallet.ui.wallet.launch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.ui.wallet.create.CreateWalletActivity;
import org.arpnetwork.eoswallet.ui.wallet.load.ImportWalletActivity;

public class LaunchFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_launch, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        LinearLayout btnView = (LinearLayout) findViewById(R.id.ll_btn_view);
        View importBtn = getLaunchItemView(R.drawable.have_account, R.string.have_account, R.string.import_account);
        View createBtn = getLaunchItemView(R.drawable.have_no_account, R.string.have_no_account, R.string.create_account);
        btnView.addView(importBtn);
        TextView tv_separator = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        tv_separator.setLayoutParams(lp);
        btnView.addView(tv_separator);
        btnView.addView(createBtn);

        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importWallet();
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWallet();
            }
        });
    }

    private View getLaunchItemView(int image, int title, int subtitle) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_launch, null);

        ImageView imageView = view.findViewById(R.id.image_bg);
        imageView.setImageResource(image);

        TextView titleView = view.findViewById(R.id.tv_title);
        titleView.setText(title);

        TextView subtitleView = view.findViewById(R.id.tv_subtitle);
        subtitleView.setText(subtitle);

        return view;
    }

    private void createWallet() {
        CreateWalletActivity.launch(getActivity());
    }

    private void importWallet() {
        ImportWalletActivity.launch(getActivity());
    }
}
