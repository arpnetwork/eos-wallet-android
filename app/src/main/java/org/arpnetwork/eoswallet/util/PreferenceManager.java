package org.arpnetwork.eoswallet.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String NAME = "EOSWallet";

    private static PreferenceManager sInstance;

    private SharedPreferences mSharedPreferences;

    private PreferenceManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        sInstance = new PreferenceManager(context);
    }

    public static void fini() {
        sInstance = null;
    }

    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    public void putFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).commit();
    }

    public void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
    }

    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).commit();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, -1);
    }

    public float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0);
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }
}
