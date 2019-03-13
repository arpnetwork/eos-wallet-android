package org.arpnetwork.eoswallet.util;

import android.app.Activity;
import android.view.View;

public class StatusBarUtil {

    public static void setLightStatusBar(Activity activity, boolean dark) {
        View decorView = activity.getWindow().getDecorView();
        if (dark) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
