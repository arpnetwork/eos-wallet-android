package org.arpnetwork.eoswallet.app;

import android.app.Application;

import org.arpnetwork.eoswallet.util.PreferenceManager;

public class EOSWalletApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        PreferenceManager.fini();
    }
}
