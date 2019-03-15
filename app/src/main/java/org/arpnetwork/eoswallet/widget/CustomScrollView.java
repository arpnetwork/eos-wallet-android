package org.arpnetwork.eoswallet.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
    private static final int UNKOWN = 0;
    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 2;

    private float mPreX;
    private float mPreY;
    private int mDirection;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//        int action = event.getAction();
//        Log.d("CustomViewPager", "event = " + action + " mPreX = " + x + " mPreY = " + y);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mPreX = x;
//                mPreY = y;
//                mDirection = UNKOWN;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mDirection == UNKOWN) {
//                    float absX = Math.abs(x - mPreX);
//                    float absY = Math.abs(y - mPreY);
//                    Log.d("CustomViewPager", "absX = " + absX + " absY = " + absY);
//                    if (absX > absY && absX > 5) {
//                        mDirection = HORIZONTAL;
//                        return true;
//                    } else {
//                        mDirection = VERTICAL;
//                    }
//                } else if (mDirection == HORIZONTAL) {
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
        return super.onInterceptTouchEvent(event);
    }
}

