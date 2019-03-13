package org.arpnetwork.eoswallet.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class QRCoverView extends View {
    private RectF mScannerRect;
    private static final int DEFAULT_SCANNER_SIZE = 280;

    private final Paint mBackgroundPain = new Paint();
    private float mScannerW;
    private float mScannerH;
    private float mTop;
    private float mLeft;

    private final Paint mCornerPain = new Paint();
    private float mCornerWidth;
    private float mCornerLength;
    private static final int DEFAULT_CORNER_WIDTH = 3;
    private static final int DEFAULT_CORNER_LENGTH = 60;

    private Context mContext;

    public QRCoverView(Context context) {
        this(context, null);
    }

    public QRCoverView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QRCoverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setScannerSize(DEFAULT_SCANNER_SIZE);
        mBackgroundPain.setColor(Color.argb((int) (0.8 * 255), 0x33, 0x33, 0x33));
        mCornerPain.setColor(0xff14ad84);
        mCornerLength = DEFAULT_CORNER_LENGTH;
        mCornerWidth = DEFAULT_CORNER_WIDTH;
        invalidate();
    }

    public void setScannerSize(int size) {
        setScannerSize(size, size);
    }

    public void setScannerSize(int width, int height) {
        mScannerW = dip2px(mContext, width);
        mScannerH = dip2px(mContext, height);
        mLeft = (getResources().getDisplayMetrics().widthPixels - mScannerW) / 2.0f;
        mTop = (getResources().getDisplayMetrics().heightPixels - mScannerH) / 2.0f;
    }

    public RectF getScannerRect() {
        return mScannerRect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mScannerRect = new RectF(mLeft, mTop, mLeft + mScannerW, mTop + mScannerH);
        drawScannerBackground(canvas, mScannerRect);
        drawScannerCorner(canvas, mScannerRect);
    }

    private void drawScannerBackground(Canvas canvas, RectF scannerRect) {
        canvas.drawRect(0, 0, scannerRect.left, getHeight(), mBackgroundPain);
        canvas.drawRect(scannerRect.left, 0, getWidth(), scannerRect.top, mBackgroundPain);
        canvas.drawRect(scannerRect.right, scannerRect.top, getWidth(), getHeight(), mBackgroundPain);
        canvas.drawRect(scannerRect.left, scannerRect.bottom, scannerRect.right, getHeight(), mBackgroundPain);
    }

    private void drawScannerCorner(Canvas canvas, RectF scannerRect) {
        canvas.drawRect(scannerRect.left, scannerRect.top, scannerRect.left + mCornerWidth,
                scannerRect.top + mCornerLength, mCornerPain);
        canvas.drawRect(scannerRect.left + mCornerWidth, scannerRect.top,
                scannerRect.left + mCornerLength, scannerRect.top + mCornerWidth, mCornerPain);
        canvas.drawRect(scannerRect.right - mCornerLength, scannerRect.top, scannerRect.right,
                scannerRect.top + mCornerWidth, mCornerPain);
        canvas.drawRect(scannerRect.right - mCornerWidth, scannerRect.top + mCornerWidth,
                scannerRect.right, scannerRect.top + mCornerLength, mCornerPain);
        canvas.drawRect(scannerRect.right - mCornerWidth, scannerRect.bottom - mCornerLength,
                scannerRect.right, scannerRect.bottom, mCornerPain);
        canvas.drawRect(scannerRect.right - mCornerLength, scannerRect.bottom - mCornerWidth,
                scannerRect.right - mCornerWidth, scannerRect.bottom, mCornerPain);
        canvas.drawRect(scannerRect.left, scannerRect.bottom - mCornerLength,
                scannerRect.left + mCornerWidth, scannerRect.bottom, mCornerPain);
        canvas.drawRect(scannerRect.left + mCornerWidth, scannerRect.bottom - mCornerWidth,
                scannerRect.left + mCornerLength, scannerRect.bottom, mCornerPain);
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
