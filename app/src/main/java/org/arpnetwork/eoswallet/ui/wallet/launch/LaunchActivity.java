package org.arpnetwork.eoswallet.ui.wallet.launch;

import android.content.Intent;
import android.os.Bundle;

import org.arpnetwork.eoswallet.HomeActivity;
import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseActivity;

public class LaunchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.my_wallet);
        hideNavigationButton();
        setContentFragment(LaunchFragment.class);
        // TODO: 判断是否已导入钱包
        if (true) {
            launchHome();
            finish();
        }
    }

    public void launchHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
    }
}
