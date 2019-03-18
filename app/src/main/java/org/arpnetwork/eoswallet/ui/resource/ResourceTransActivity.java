package org.arpnetwork.eoswallet.ui.resource;

import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;

public class ResourceTransActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(ResourceTransFragment.class);
    }
}
