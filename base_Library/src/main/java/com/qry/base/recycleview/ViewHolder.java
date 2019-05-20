package com.qry.base.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * className：MultiItemTypeAdapter
 * author：RonQian
 * created by：2018/7/24 17:30
 * update by：2018/7/24 17:30
 * 用途：  ViewHolder
 * 修改备注：
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private Unbinder mUnbinder;
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;


    public ViewHolder(Context context, View itemView, RecyclerView.Adapter<ViewHolder> adapter) {
        super(itemView);
        mUnbinder = ButterKnife.bind(adapter, itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static ViewHolder createViewHolder(Context context, View itemView,
                                              RecyclerView.Adapter<ViewHolder> adapter) {
        ViewHolder holder = new ViewHolder(context, itemView, adapter);

        return holder;
    }

    public static ViewHolder createViewHolder(Context context,
                                              ViewGroup parent, int layoutId,
                                              RecyclerView.Adapter<ViewHolder> adapter) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, null,
                false);
        ViewHolder holder = new ViewHolder(context, itemView, adapter);
        return holder;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId 控件
     * @return 视图
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }


    /****以下为辅助方法*****/

    /**
     * 设置TextView的值
     *
     * @param viewId 控件Id
     * @param text 内容
     * @return 奋斗
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public TextView getTextView(int viewId) {
        TextView tv = getView(viewId);

        return tv;
    }



    public ViewHolder setInputType(int viewId, int type) {
        EditText et = getView(viewId);
        et.setInputType(type);
        return this;

    }

    public String getText(int viewId) {
        EditText et = getView(viewId);
        String str = et.getText().toString().trim();
        return str;
    }

    public ViewHolder setHint(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setHint(text);
        return this;
    }

    public ViewHolder addTextChangedListener(int viewId, TextWatcher watcher) {
        EditText et = getView(viewId);
        et.addTextChangedListener(watcher);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setAdapter(int viewId, RecyclerView.Adapter<ViewHolder> adapter, int type) {
        RecyclerView view = getView(viewId);
        if (type == 0) {

            view.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
        view.setAdapter(adapter);
        return this;
    }

    public ViewHolder setAdapter(int viewId, ArrayAdapter<String> adapter) {
        Spinner view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    public ViewHolder setOnItemSelectedListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        Spinner view = getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public ViewHolder setAlpha(int viewId, float value) {
        getView(viewId).setAlpha(value);
        return this;
    }

    public ViewHolder setOnCheckedChangeListener(int viewId, RadioGroup.OnCheckedChangeListener listener) {
        RadioGroup view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CheckBox view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId,
                                         View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }


    public AppCompatEditText getEditView(int viewId) {
        AppCompatEditText view = getView(viewId);
        return view;
    }

}
