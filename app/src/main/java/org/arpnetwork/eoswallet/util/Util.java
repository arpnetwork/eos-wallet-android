package org.arpnetwork.eoswallet.util;

import android.text.TextUtils;

public class Util {
    /**
     * 判断密码强度
     *
     * @param passwordStr 输入字符串
     * @return 0：空字符 -1：非法输入 其他:密码强度
     */
    public static int evaluatePassword(String passwordStr) {
        if (TextUtils.equals("", passwordStr)) {
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
            return 3;
        }
        return -1;
    }
}
