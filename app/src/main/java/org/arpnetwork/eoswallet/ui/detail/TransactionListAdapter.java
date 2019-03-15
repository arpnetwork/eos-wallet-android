package org.arpnetwork.eoswallet.ui.detail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.arpnetwork.eoswallet.data.TransactionData;

import java.util.ArrayList;
import java.util.List;

public class TransactionListAdapter extends BaseAdapter {
    private Context mContext;
    private List<TransactionData> mList;

    public TransactionListAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public void addData(List<TransactionData> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TransactionItem(mContext);
        }
        return convertView;
    }
}
