package org.arpnetwork.eoswallet.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private static final int UNKOWN = 0;
    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 2;

    private float mPreX;
    private float mPreY;
    private int mDirection;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPreX = x;
                mPreY = y;
                mDirection = UNKOWN;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mDirection == UNKOWN) {
                    float absX = Math.abs(x - mPreX);
                    float absY = Math.abs(y - mPreY);
                    if (absX > absY && absX > 5) {
                        mDirection = HORIZONTAL;
                        return true;
                    } else {
                        mDirection = VERTICAL;
                    }
                } else if (mDirection == HORIZONTAL) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}

