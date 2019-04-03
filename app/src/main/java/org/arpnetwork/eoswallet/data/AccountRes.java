package org.arpnetwork.eoswallet.data;

import java.util.List;
import java.util.Locale;

public class AccountRes {
    public int code;
    public String detail;
    public List<String> data;

    public String getAccount() {
        if (data != null && data.size() > 0) {
            return data.get(0);
        }
        return "";
    }

    public String getOwnerKey() {
        if (data != null && data.size() > 1) {
            return data.get(1);
        }
        return "";
    }

    public String getActiveKey() {
        if (data != null && data.size() > 2) {
            return data.get(2);
        }
        return "";
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "{code: %d, detail: %s, data: %s}", code, detail, data);
    }
}
