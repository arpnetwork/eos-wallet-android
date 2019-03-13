package org.arpnetwork.eoswallet.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import org.arpnetwork.eoswallet.R;
import org.arpnetwork.eoswallet.util.UIHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFragment extends Fragment {
    private static final Logger LOG = LoggerFactory.getLogger(BaseFragment.class);

    private FragmentActivity mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        LOG.debug("onAttach. [{}]", getClass().getSimpleName());

        mContext = (FragmentActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LOG.debug("onCreate. [{}]", getClass().getSimpleName());

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        LOG.debug("onCreateView. [{}]", getClass().getSimpleName());

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LOG.debug("onViewCreated. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LOG.debug("onActivityCreated. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();

        LOG.debug("onStart. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();

        LOG.debug("onResume. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();

        LOG.debug("onPause. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();

        LOG.debug("onStop. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LOG.debug("onDestroyView. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LOG.debug("onDestroy. [{}]", getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();

        LOG.debug("onDetach. [{}]", getClass().getSimpleName());
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected void setTitle(CharSequence title) {
        if (getActivity() != null) getActivity().setTitle(title);
    }

    protected void setTitle(int resId) {
        setTitle(getString(resId));
    }

    protected final View findViewById(int viewId) {
        return getView().findViewById(viewId);
    }

    protected void finish() {
        LOG.debug("finish.");

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            getActivity().finish();
        }
    }

    protected void showToast(CharSequence text, int duration) {
        Context context = getActivity();
        if (context != null) {
            UIHelper.showToast(context, text, duration);
        }
    }

    protected void showToast(int resId, int duration) {
        showToast(getString(resId), duration);
    }

    private ProgressDialog mProgressDialog;

    protected void showProgressBar(String msg) {
        showProgressBar(msg, true);
    }

    protected void showProgressBar(String msg, boolean cancel) {
        mProgressDialog = ProgressDialog.show(getActivity(), null, msg);
        mProgressDialog.setCanceledOnTouchOutside(cancel);
        mProgressDialog.setCancelable(cancel);
    }

    protected void hideProgressBar() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void setContentFragment(Class<? extends BaseFragment> fragmentClass, Bundle arguments) {
        LOG.debug("set content fragment. class={}", fragmentClass.getName());

        Fragment fragment = Fragment.instantiate(mContext, fragmentClass.getName(), arguments);
        FragmentTransaction t = mContext.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.content_frame, fragment);
        t.commit();
    }

    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected View getContentView() {
        return null;
    }

    protected void showContentView() {
        if (getContentView() != null) {
            getContentView().setVisibility(View.VISIBLE);
        }
    }
}
