package com.qry.base.util;

import android.content.Context;

/**
 * LvUtils
 *
 * @author lvfq
 * @date 2017/6/19 上午9:58
 * @mainFunction :
 */

public class LvUtils {

    private static LvUtils util;
    private Context context;

    public synchronized static LvUtils init(Context context) {
        if (util == null) {
            synchronized (LvUtils.class) {
                util = new LvUtils(context);
            }
        }
        return util;
    }

    /**
     * 初始化工具类中使用的 Context
     *
     * @param context
     */
    public LvUtils(Context context) {
        this.context = context;
    }

    public static Context getContext() {
        if (EmptyUtil.isNotNull(util) && EmptyUtil.isNotNull(util.context)) {
            return util.context;
        }
        throw new NullPointerException("u should init first - > LvUtils.init(Context) in your Application");
    }



}
