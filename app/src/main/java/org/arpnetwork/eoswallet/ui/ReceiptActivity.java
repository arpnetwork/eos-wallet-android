package org.arpnetwork.eoswallet.ui;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;
import org.arpnetwork.eoswallet.base.BaseFragment;

public class ReceiptActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(ReceiptFragment.class);
    }
}
