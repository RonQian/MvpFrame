package com.qry.base.model.dto;

/**
 * 所有的枚举都需要有一个接口返回即值和描述对客户端调用
 */
public class EnumTypeDto {
    private int value;
    private String text;
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
