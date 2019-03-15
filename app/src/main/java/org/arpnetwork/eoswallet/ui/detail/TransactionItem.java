package org.arpnetwork.eoswallet.ui.detail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import org.arpnetwork.eoswallet.R;

public class TransactionItem extends LinearLayout {

    public TransactionItem(Context context) {
        super(context);

        init(context);
    }

    public TransactionItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_trade, this, true);
    }
}
