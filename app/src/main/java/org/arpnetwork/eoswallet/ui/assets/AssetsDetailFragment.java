package org.arpnetwork.eoswallet.ui.assets;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.ui.ReceiptActivity;
import org.arpnetwork.eoswallet.ui.transfer.TransferActivity;
import org.arpnetwork.eoswallet.util.UIHelper;
import org.arpnetwork.eoswallet.widget.SlidingTabLayout;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.Locale;

public class AssetsDetailFragment extends BaseFragment implements View.OnClickListener, ObservableScrollViewCallbacks {
    private static final String TAG = AssetsDetailFragment.class.getSimpleName();

    private LinearLayout mTopLayout;

    private int mScrollY;
    private ScrollState mScrollState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assets_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        getBaseActivity().hideTitleDivider();
        setTitle(String.format(Locale.US, getString(R.string.assets_format), "EOS"));
    }

    private void initViews() {
        mTopLayout = (LinearLayout) findViewById(R.id.layout_top);

        TextView totalAssets = (TextView) findViewById(R.id.tv_total_assets);
        totalAssets.setText(String.format(Locale.US, getString(R.string.total_assets_format), "EOS"));

        TextView curPrice = (TextView) findViewById(R.id.tv_cur_price);
        curPrice.setText(String.format(Locale.US, getString(R.string.current_price), "EOS"));

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager());
        adapter.setScrollViewCallback(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorThickness(UIHelper.dip2px(getActivity(), 2));
        slidingTabLayout.setSelectedIndicatorWidth(UIHelper.dip2px(getActivity(), 30));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.tab_indicator_text_pressed));
        slidingTabLayout.setDividerColors(Color.TRANSPARENT);
        slidingTabLayout.setViewPager(viewPager);

        findViewById(R.id.layout_receipt).setOnClickListener(this);
        findViewById(R.id.layout_transfer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_receipt:
                startActivity(ReceiptActivity.class);
                break;
            case R.id.layout_transfer:
                startActivity(TransferActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        Log.d(TAG, "onScrollChanged. scrollY = " + scrollY);
        mScrollY = scrollY;
        if (mScrollState == ScrollState.DOWN && mScrollY == 0) {
            mTopLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        Log.d(TAG, "onUpOrCancelMotionEvent. scrollState = " + scrollState);
        mScrollState = scrollState;
        if (scrollState == ScrollState.UP) {
            mTopLayout.setVisibility(View.GONE);
        } else if (scrollState == ScrollState.DOWN && mScrollY == 0) {
            mTopLayout.setVisibility(View.VISIBLE);
        }
    }

    private static class FragmentPagerAdapter extends CacheFragmentStatePagerAdapter {
        private static final String[] TITLES = {"全部", "转入", "转出"};
        private TransactionListFragment mTransactionAllFragment;
        private TransactionListFragment mTransactionInFragment;
        private TransactionListFragment mTransactionOutFragment;

        public FragmentPagerAdapter(FragmentManager fm) {
            super(fm);

            mTransactionAllFragment = new TransactionListFragment();
            mTransactionInFragment = new TransactionListFragment();
            mTransactionOutFragment = new TransactionListFragment();
        }

        public void setScrollViewCallback(ObservableScrollViewCallbacks callback) {
            mTransactionAllFragment.setScrollViewCallback(callback);
            mTransactionInFragment.setScrollViewCallback(callback);
            mTransactionOutFragment.setScrollViewCallback(callback);
        }

        @Override
        protected Fragment createItem(int position) {
            Log.d(TAG, "createItem. pos = " + position);

            if (position == 0) {
                return mTransactionAllFragment;
            } else if (position == 1) {
                return mTransactionInFragment;
            } else {
                return mTransactionOutFragment;
            }
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }
}
