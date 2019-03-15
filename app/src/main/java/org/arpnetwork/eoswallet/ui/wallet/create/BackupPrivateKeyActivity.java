package org.arpnetwork.eoswallet.ui.wallet.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseActivity;
import org.arpnetwork.eoswallet.misc.Constant;

public class BackupPrivateKeyActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.backup_private_key);
        setContentFragment(BackupPrivateKeyFragment.class);
    }

    /**
     *
     * @param activity
     * @param privateKey 显示和检测的私钥
     * @param type       0: 读取私钥  1:输入私钥
     */
    public static void launch(Activity activity, String privateKey, int type) {
        Intent intent = new Intent(activity, BackupPrivateKeyActivity.class);
        intent.putExtra(Constant.PRIVAT_KEY, privateKey);
        intent.putExtra(Constant.BACKUP_TYPE, type);
        activity.startActivity(intent);
    }
}
