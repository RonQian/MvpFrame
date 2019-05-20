package com.qry.base.view.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.qry.base.R;
import com.qry.base.loader.LoadingDialog;
import com.qry.base.presenter.IBasePresenter;
import com.qry.base.preview.transfer.TransferConfig;
import com.qry.base.preview.transfer.Transferee;
import com.qry.base.util.ToastUtil;
import com.qry.base.view.IBaseView;
import com.roger.gifloadinglibrary.GifLoadingView;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//基类Activity
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    private TextView mToolBarTitle;// 显示在中间的文字信息
    protected Context mContext;
    private Unbinder mUnbinder;
    protected P mPresenter;// 对presenter的引用

    protected abstract int getLayoutId();

    protected abstract void initView();

    /*给mPresenter 符值*/
    protected abstract P createPresenter();

    protected Transferee transferee;

    protected TransferConfig config;

    protected DisplayImageOptions options;
    private LoadingDialog mShowDialog;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transferee = Transferee.getDefault(this);
        options = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.mipmap.ic_empty_photo)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .build();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        setContentView(getLayoutId());
        mContext = this;
        mUnbinder = ButterKnife.bind(this);

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initToolBar(getTitle().toString());
        initView();
        setHalfTransparent();
        setFitSystemWindow();
    }

    @Override
    public void showLoading() {
        showGifLoading(true);
    }

    @Override
    public void hideLoading() {
        showGifLoading(false);
    }


    /**
     * toast 方式 显示信息
     *
     * @param msg  需要显示的信息
     * @param type 类型 0:info -1:error 1:success 2:warning
     * @return
     * @author penghj
     * @Date 2018/7/27 10:57
     */
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


    public void showSuccess(String msg) {
        showMsg(msg, 1);
    }

    public void showError(String msg) {
        showMsg(msg, -1);

    }

    public void showWarning(String msg) {
        showMsg(msg, 2);

    }

    public void showInfo(String msg) {
        showMsg(msg, 0);
    }

    /*显示一个gif的LOADING动画模态框*/
    protected void showGifLoading(boolean show) {
        if (show) {
            if (mShowDialog == null) {
                mShowDialog = new LoadingDialog(this);
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
     * title带返回箭头
     *
     * @param title 标题
     * @return
     */
    public void initToolBar(String title) {

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setTitle("");//设置默认的title为"",即不显示靠左的title
            mToolBarTitle = toolbar.findViewById(R.id.toolbar_title);
            mToolBarTitle.setText(title);
        }
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(getLayoutId() != R.layout.activity_main);// main activity 不带返回
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
    }

    protected void setTitle(String title) {
        mToolBarTitle.setText(title);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onDestroy() {
        System.gc();
        System.runFinalization();
        super.onDestroy();
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 半透明状态栏
     */
    protected void setHalfTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color_tab));

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 如果需要内容紧贴着StatusBar
     * 应该在对应的xml布局文件中，设置根布局fitsSystemWindows=true。
     */
    private View contentViewGroup;

    protected void setFitSystemWindow() {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(true);
    }

}
