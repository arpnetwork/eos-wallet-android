package org.arpnetwork.eoswallet.ui.transfer;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;

public class TransferActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(TransferFragment.class);
    }
}
