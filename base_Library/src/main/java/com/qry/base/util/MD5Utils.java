package com.qry.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * className：MD5Utils
 * author：RonQian
 * created by：2019/3/7 15:44
 * update by：2019/3/7 15:44
 * 用途：  MD5加密
 * 修改备注：
 */
public class MD5Utils {

    public static String md5Password(String password) {
        StringBuilder sb = new StringBuilder();
        // 得到一个信息摘要器
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            // 把每一个byte做一个与运算 0xff
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
