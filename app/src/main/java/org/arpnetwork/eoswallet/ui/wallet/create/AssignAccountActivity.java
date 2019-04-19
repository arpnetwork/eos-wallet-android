package org.arpnetwork.eoswallet.ui.wallet.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseActivity;
import org.arpnetwork.eoswallet.misc.Constant;

public class AssignAccountActivity extends BaseActivity {    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(R.string.assign_account);
    setContentFragment(AssignAccountFragment.class);
}

    /**
     *
     * @param activity
     * @param accountName 分配到的账号名称
     */
    public static void launch(Activity activity, String accountName) {
        Intent intent = new Intent(activity, AssignAccountActivity.class);
        intent.putExtra(Constant.ACCOUNT_NAME, accountName);
        activity.startActivity(intent);
    }
}
