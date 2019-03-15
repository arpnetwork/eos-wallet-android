package org.arpnetwork.eoswallet.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.R;

public class ExploreFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle(R.string.tab_explore);
        getBaseActivity().showToolbar();
    }
}
