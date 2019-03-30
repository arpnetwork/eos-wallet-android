package org.arpnetwork.eoswallet.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle(R.string.personal_center);
        getBaseActivity().showToolbar();
    }

    private void initViews() {
        findViewById(R.id.layout_wallet_manage).setOnClickListener(this);
        findViewById(R.id.layout_message_manage).setOnClickListener(this);
        findViewById(R.id.item_security).setOnClickListener(this);
        findViewById(R.id.item_help).setOnClickListener(this);
        findViewById(R.id.item_invite).setOnClickListener(this);
        findViewById(R.id.item_about).setOnClickListener(this);
        findViewById(R.id.item_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_wallet_manage:
                break;
            case R.id.layout_message_manage:
                break;
            case R.id.item_security:
                break;
            case R.id.item_help:
                break;
            case R.id.item_invite:
                break;
            case R.id.item_about:
                break;
            case R.id.item_settings:
                break;
            default:
                break;
        }
    }
}
