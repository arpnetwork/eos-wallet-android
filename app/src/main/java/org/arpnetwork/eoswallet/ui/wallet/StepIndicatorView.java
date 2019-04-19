package org.arpnetwork.eoswallet.ui.wallet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;

public class StepIndicatorView extends FrameLayout {
    private LinearLayout mStepLL;

    public StepIndicatorView(Context context) {
        this(context, null);
    }

    public StepIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.item_step_view, this, true);
    }

    public void setTotalAndIndicate(int total, int indicate) {
        mStepLL = findViewById(R.id.ll_steps);
        addSplitView();
        for (int i = 0; i < total; i ++) {
            addStepView((i + 1), (i + 1) == indicate);
            addSplitView();
        }
    }

    public void addSplitView() {
        View view = new View(getContext());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.weight = 1;
        mStepLL.addView(view, p);
    }

    public void addStepView(int step, boolean selected) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_step, null);
        if (selected) {
            textView.setTextColor(Color.WHITE);
            textView.setBackground(getResources().getDrawable(R.drawable.item_step_selected_bg));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView.setTextSize(24);
        } else {
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            textView.setBackground(getResources().getDrawable(R.drawable.item_step_normal_bg));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textView.setTextSize(18);
        }
        textView.setText("" + step);
        mStepLL.addView(textView);
    }
}
