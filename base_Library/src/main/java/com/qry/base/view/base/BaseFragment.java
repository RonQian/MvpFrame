package com.qry.base.view.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.qry.base.R;
import com.qry.base.loader.LoadingDialog;
import com.qry.base.presenter.IBasePresenter;
import com.qry.base.util.L;
import com.qry.base.util.ToastUtil;
import com.qry.base.view.IBaseView;
import com.roger.gifloadinglibrary.GifLoadingView;

import java.util.Objects;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * className：BaseFragment
 * author：RonQian
 * created by：2018/7/30 15:59
 * update by：2018/7/30 15:59
 * 用途： Fragment 基类
 * 修改备注：
 */

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {
    private static final String TAG = BaseFragment.class.getSimpleName();
    public BaseActivity mActivity;
    protected BaseFragment mFragment;
    protected Context mContext;
    private Unbinder mUnbinder;

    protected P mPresenter;// 对presenter的引用

    private AlertDialog alertDialog;

    private LoadingDialog mShowDialog;

    protected abstract Object getLayoutId();

    protected abstract void initView();

    /*给mPresenter 符值*/
    protected abstract P createPresenter();

    //是否可见
    protected boolean visible = false;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            visible = true;
            onVisible();
        } else {
            visible = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        //加载数据
        loadData();
    }

    protected abstract void loadData();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragment = this;
        mActivity = (BaseActivity) context;
        mContext = mActivity.getApplicationContext();
    }
    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.i(TAG, "----onCreateView---");
        View rootView;
        if (getLayoutId() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLayoutId(), container, false);
        } else if (getLayoutId() instanceof View) {
            rootView = (View) getLayoutId();
        } else {
            L.e(TAG, "setLayout() type must be int or View");
            throw new RuntimeException("setLayout() type must be int or View");
        }

        if (rootView != null) mUnbinder = ButterKnife.bind(this, rootView);

        mContext = getActivity();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        return rootView;
    }


    /*显示一个gif的LOADING动画模态框*/
    protected void showGifLoading(boolean show) {
        if (show) {
            if (mShowDialog == null) {
                mShowDialog = new LoadingDialog(mActivity);
            }
            mShowDialog.setCancelable(false);
            mShowDialog.setCanceledOnTouchOutside(false);
            mShowDialog.show();
        } else {
            if (mShowDialog != null && mShowDialog.isShowing()) {
                mShowDialog.dismiss();
            }
        }
    }

    /**
     * 获取activity
     *
     * @return 当前Activity
     */
    public final BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showLoading() {
        if (mShowDialog == null) {
            mShowDialog = new LoadingDialog(mActivity);
        }
        mShowDialog.setCancelable(false);
        mShowDialog.setCanceledOnTouchOutside(false);
        mShowDialog.show();
    }

    @Override
    public void hideLoading() {
//        showGifLoading(false);
        if (null != mShowDialog) {
            mShowDialog.dismiss();
        }
    }

    @Override
    public void showMsg(String msg, int type) {
        if (type == 0) {
            ToastUtil.info(mContext, msg);
        } else if (type == -1) {
            ToastUtil.error(mContext, msg);
        } else if (type == 1) {
            ToastUtil.success(mContext, msg);
        } else if (type == 2) {
            ToastUtil.warning(mContext, msg);
        }
    }

    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(mActivity).create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_view);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }


}
