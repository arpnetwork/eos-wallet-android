package org.arpnetwork.eoswallet.ui.wallet.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseActivity;

public class CreateWalletActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.create_account);
        setContentFragment(CreateWalletFragment.class);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CreateWalletActivity.class);
        activity.startActivity(intent);
    }
}
