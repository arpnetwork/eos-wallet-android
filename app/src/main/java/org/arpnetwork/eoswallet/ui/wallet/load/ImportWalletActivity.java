package org.arpnetwork.eoswallet.ui.wallet.load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.arpnetwork.eoswallet.base.BaseActivity;

public class ImportWalletActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentFragment(ImportWalletFragment.class);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ImportWalletActivity.class);
        activity.startActivity(intent);
    }
}
