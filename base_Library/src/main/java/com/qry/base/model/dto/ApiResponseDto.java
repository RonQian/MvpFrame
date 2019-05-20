package com.qry.base.model.dto;

import android.support.annotation.NonNull;

/*
 * 调用Mapi返回的结果
 *
 * @return
 *
 * @Date 2018/7/27 13:10
 */
public class ApiResponseDto<T> {
    private int state;    // 此次执行的状态 0：表示失败 ；1：表示成功 -1:表示没有登陆 -2:表示没有权限
    private String msg; // 返回的提示信息，成功或是失败都可以付值
    private long itemNum; // 返回结果集的数量，主要用于前端的控制的分页
    private T data; // 返回的数据

    /*getter setter*/
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getItemNum() {
        return itemNum;
    }

    public void setItemNum(long itemNum) {
        this.itemNum = itemNum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @NonNull
    public String toString() {
        return "state:" + state + " |message:" + msg + "  |content:" + getData().toString();
    }
}
