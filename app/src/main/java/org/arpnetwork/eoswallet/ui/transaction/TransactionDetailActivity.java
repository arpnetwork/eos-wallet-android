package org.arpnetwork.eoswallet.ui.transaction;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;
import org.arpnetwork.eoswallet.ui.assets.AssetsDetailFragment;

public class TransactionDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(TransactionDetailFragment.class);
    }
}
