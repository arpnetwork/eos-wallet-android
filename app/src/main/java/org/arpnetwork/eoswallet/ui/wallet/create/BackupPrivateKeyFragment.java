package org.arpnetwork.eoswallet.ui.wallet.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.base.BaseFragment;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.ui.wallet.StepIndicatorView;
import org.arpnetwork.eoswallet.util.UIHelper;

public class BackupPrivateKeyFragment extends BaseFragment {
    private EditText mPrivateKeyET;
    private View mNoPhotoView;
    private TextView mDetailView;
    private Button mNextStepBtn;

    private String mPrivateKeyString;
    private int mBackupType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_backup_private_key, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPrivateKeyString = bundle.getString(Constant.PRIVAT_KEY);
            mBackupType = bundle.getInt(Constant.BACKUP_TYPE);
        }
        initView();
    }

    private void initView() {
        StepIndicatorView stepView = (StepIndicatorView) findViewById(R.id.step_view);
        stepView.setTotalAndIndicate(3, 2);
        mPrivateKeyET = (EditText) findViewById(R.id.et_private_key);
        mDetailView = (TextView) findViewById(R.id.tv_detail);
        mNextStepBtn = (Button) findViewById(R.id.btn_next_step);
        if (mBackupType == 0) {
            setRead();
        } else {
            setWrite();
        }
    }

    private void setRead() {
        mPrivateKeyET.setText(mPrivateKeyString);
//        mPrivateKeyET.setEnabled(false);
        mDetailView.setText(R.string.backup_private_key_detail);
        mNextStepBtn.setText(R.string.known);
        mNextStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                initNoPhotoTip();
                BackupPrivateKeyActivity.launch(getActivity(), mPrivateKeyString, 1);
            }
        });
    }

    private void setWrite() {
        mPrivateKeyET.setText("");
        mPrivateKeyET.setEnabled(true);
        mDetailView.setText(R.string.check_your_private_key_detail);
        mNextStepBtn.setText(R.string.next_step);
        mNextStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPrivateKeyET.getText().toString().equals(mPrivateKeyString)) {
                    showProgressView();
//                    UIHelper.showImageToast(getContext(), R.drawable.toast_success, R.string.check_success);
                    assignAccount();
                } else {
                    UIHelper.showImageToast(getContext(), R.drawable.toast_failed, R.string.check_failed);
                }
            }
        });
    }

    private void initNoPhotoTip() {
        mNoPhotoView = LayoutInflater.from(getContext()).inflate(R.layout.view_no_photo, null);

        UIHelper.setIndentationText(getContext(), (TextView) mNoPhotoView.findViewById(R.id.tv_detail_1), getString(R.string.no_photo_detail_1));
        UIHelper.setIndentationText(getContext(), (TextView) mNoPhotoView.findViewById(R.id.tv_detail_2), getString(R.string.no_photo_detail_2));

        ((ViewGroup) findViewById(R.id.fl_root)).addView(mNoPhotoView);
        mNoPhotoView.setVisibility(View.GONE);

        findViewById(R.id.btn_known).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNoPhotoView.setVisibility(View.GONE);
                BackupPrivateKeyActivity.launch(getActivity(), mPrivateKeyString, 1);
            }
        });
    }

    private void showProgressView() {
        findViewById(R.id.pb_assign_account).setVisibility(View.VISIBLE);
    }

    private void assignAccount() {
        // TODO：分配账号
        AssignAccountActivity.launch(getActivity(), "testAccount");
    }
}
