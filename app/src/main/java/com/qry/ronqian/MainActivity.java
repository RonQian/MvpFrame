package com.qry.ronqian;

import android.os.Bundle;
import android.widget.TextView;

import com.qry.base.presenter.IBasePresenter;
import com.qry.base.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mTv_hello)
    TextView mTvHello;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTvHello.setText("我是MVP的框架");
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
