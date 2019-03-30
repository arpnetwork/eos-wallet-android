package org.arpnetwork.eoswallet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;

public class MyItem extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public MyItem(Context context) {
        super(context);
    }

    public MyItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.item_my, this, true);

        mImageView = findViewById(R.id.iv_icon);
        mTextView = findViewById(R.id.tv_title);

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyItem);
            String text = typedArray.getString(R.styleable.MyItem_item_text);
            int drawableId = typedArray.getResourceId(R.styleable.MyItem_item_icon, 0);
            typedArray.recycle();

            mImageView.setImageResource(drawableId);
            mTextView.setText(text);
        }
    }
}

