package org.arpnetwork.eoswallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import org.arpnetwork.eoswallet.base.BaseActivity;
import org.arpnetwork.eoswallet.ui.explore.ExploreFragment;
import org.arpnetwork.eoswallet.ui.mine.MineFragment;
import org.arpnetwork.eoswallet.ui.wallet.WalletFragment;
import org.arpnetwork.eoswallet.wedgit.OnTabReselectListener;

public class HomeActivity extends BaseActivity {

    private static final Class mFragmentArray[] = {WalletFragment.class, ExploreFragment.class, MineFragment.class};
    private static final int mTabIconArray[] = {R.drawable.tab_wallet_selector, R.drawable.tab_explore_selector, R.drawable.tab_mine_selector};
    private static final int mTabTextArray[] = {R.string.tab_wallet, R.string.tab_explore, R.string.tab_mine};

    private LayoutInflater mLayoutInflater;
    private FragmentTabHost mTabHost;
    private TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initViews() {
        mTitleView = findViewById(R.id.tv_title);
        mTitleView.setText(getString(R.string.tab_wallet));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar(toolbar);
        getSupportActionBar().setTitle(null);
//        hideToolbar();

        mLayoutInflater = LayoutInflater.from(this);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragmentArray.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(mTabTextArray[i])).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
        }

        //当重新点击导当前tab的时候刷新当前fragment并消费掉点击事件
        for (int j = 0; j < mTabHost.getTabWidget().getChildCount(); j++) {
            mTabHost.getTabWidget().getChildTabViewAt(j).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    HomeActivity.super.onTouchEvent(motionEvent);
                    boolean consumed = false;
                    // use getTabHost().getCurrentTabView to decide if the current tab is
                    // touched again
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN
                            && view.equals(mTabHost.getCurrentTabView())) {
                        // use getTabHost().getCurrentView() to get a handle to the view
                        // which is displayed in the tab - and to get this views context
                        Fragment currentFragment = getCurrentFragment();

                        if (currentFragment != null
                                && currentFragment instanceof OnTabReselectListener) {
                            OnTabReselectListener listener = (OnTabReselectListener) currentFragment;

                            listener.onTabReselect();
                            consumed = true;
                        }
                    }
                    return consumed;
                }
            });
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mTitleView.setText(tabId);
            }
        });
    }


    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabHost.getCurrentTabTag());
    }

    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.item_tab, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mTabIconArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTabTextArray[index]);

        return view;
    }
}
