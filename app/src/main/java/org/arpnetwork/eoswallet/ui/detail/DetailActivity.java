package org.arpnetwork.eoswallet.ui.detail;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(DetailFragment.class);
    }
}
