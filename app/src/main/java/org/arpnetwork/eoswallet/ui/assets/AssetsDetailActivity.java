package org.arpnetwork.eoswallet.ui.assets;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;

public class AssetsDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(AssetsDetailFragment.class);
    }
}
