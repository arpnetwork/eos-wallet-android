package org.arpnetwork.eoswallet.ui.assets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.data.TransactionData;
import org.arpnetwork.eoswallet.ui.transaction.TransactionDetailActivity;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;

import java.util.ArrayList;

public class TransactionListFragment extends BaseFragment {
    private static final String TAG = TransactionListFragment.class.getSimpleName();

    private TransactionListAdapter mAdapter;
    private ObservableScrollViewCallbacks mObservableScrollViewCallbacks;

    public void setScrollViewCallback(ObservableScrollViewCallbacks callback) {
        mObservableScrollViewCallbacks = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new TransactionListAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {
        ObservableListView listView = (ObservableListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(mOnItemClickListener);
        listView.setScrollViewCallbacks(mObservableScrollViewCallbacks);
        listView.setAdapter(mAdapter);

        ArrayList<TransactionData> items = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            TransactionData data = new TransactionData();
            data.account = "";
            data.amount = 0;
            data.time = System.currentTimeMillis();
            items.add(data);
        }
        mAdapter.addData(items);
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(TransactionDetailActivity.class);
        }
    };
}
