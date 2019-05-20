package com.qry.base.model.dto;

/**
 * className：MouldApiResponseDto
 * author：RonQian
 * created by：2018/12/12 10:34
 * update by：2018/12/12 10:34
 * 修改备注：
 */
public class  MouldApiResponseDto {
    private int code;
    private String message;
    private String developerMessage;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
