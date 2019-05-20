package com.qry.base.model.dto;


import com.qry.base.util.EnumDes;

public class EnumData {


    /**
     * 用户类型
     */
    public enum UserType {
        @EnumDes(des = "普通用户")
        Normal,
        @EnumDes(value = 1, des = "管理员用户")
        Admin,
        @EnumDes(value = 2, des = "系统用户")
        Sys,
        @EnumDes(value = 3, des = "质检用户")
        QC
    }


}
