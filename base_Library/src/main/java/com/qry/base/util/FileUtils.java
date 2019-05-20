package com.qry.base.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * className：FileUtils
 * author：RonQian
 * created by：2019/1/2 14:21
 * update by：2019/1/2 14:21
 * 用途： 文件操作
 * 修改备注：
 */
public class FileUtils {
    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        FileUtils.deleteDirWihtFile(dir);
    }
    //删除指定文件夹下的文件
    private static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
        }
    }
    /**
     * 获取SD下的应用目录
     */
    private static String getExternalStoragePath(Context mContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        String ROOT_DIR = "Android/data/" + mContext.getPackageName();
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString()+File.separator;
    }

    /**
     * 获取拍照的目录
     */
    public static String generateImgePath(Context context) {
        return getExternalStoragePath(context) + File.separator;
    }
}
