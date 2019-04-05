package org.arpnetwork.eoswallet.ui.transaction;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;

public class TransactionDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(TransactionDetailFragment.class);
    }
}
