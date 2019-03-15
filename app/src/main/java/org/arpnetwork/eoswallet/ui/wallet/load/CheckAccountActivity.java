package org.arpnetwork.eoswallet.ui.wallet.load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseActivity;
import org.arpnetwork.eoswallet.misc.Constant;

public class CheckAccountActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.check_private_key);
        setContentFragment(CheckAccountFragment.class);
    }

    public static void launch(Activity activity, String privateKey) {
        Intent intent = new Intent(activity, CheckAccountActivity.class);
        intent.putExtra(Constant.PRIVAT_KEY, privateKey);
        activity.startActivity(intent);
    }
}
