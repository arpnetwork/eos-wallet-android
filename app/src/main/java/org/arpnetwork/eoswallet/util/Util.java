package org.arpnetwork.eoswallet.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import org.arpnetwork.eoswallet.blockchain.util.GsonEosTypeAdapterFactory;
import com.google.gson.GsonBuilder;

public class Util {
    /**
     * 判断密码强度
     *
     * @param passwordStr 输入字符串
     * @return 0：空字符 -1：非法输入 其他:密码强度
     */
    public static int evaluatePassword(String passwordStr) {
        if (TextUtils.isEmpty(passwordStr) || passwordStr.length() < 8) {
            return 0;
        }
        if (passwordStr.matches("\\d*") ||
                passwordStr.matches("[a-zA-Z]+") ||
                passwordStr.matches("\\W+$")) {
            return 1;
        }
        if (passwordStr.matches("\\D*") ||
                passwordStr.matches("[\\d\\W]*") ||
                passwordStr.matches("\\w*")) {
            return 2;
        }
        if (passwordStr.matches("[\\w\\W]*")) {
            if (passwordStr.matches(".*[a-z].*") && passwordStr.matches(".*[A-Z].*")) {
                return 4;
            }
            return 3;
        }
        return -1;
    }

    public static long parseLongSafely(String content, int defaultValue) {
        if ( null == content) return defaultValue;

        try {
            return Long.parseLong(content);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int parseIntSafely(String content, int defaultValue) {
        if ( null == content) return defaultValue;

        try {
            return Integer.parseInt(content);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String prettyPrintJson(Object object) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new GsonEosTypeAdapterFactory())
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create().toJson(object);
    }

    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setPrimaryClip(ClipData.newPlainText(null, text));
    }
}
