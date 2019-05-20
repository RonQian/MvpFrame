package com.qry.base.model.dto;



public class UserDto {
    private long id;// 自增ID
    private String no;// 帐号（用于登陆）
    private long workTeamId;// 工作班组
    private String name;// 用户名
    private String pwd;// 密码 MD5加密
    private boolean usable;// 是否启用：1：启用;0:停用
    private long organizeId;
    private EnumData.UserType type = EnumData.UserType.Normal;// 用户类型

    //扩展字段
    private String workTeamName;// 工作班组名称
    private String organizeName;// 组织名称
    private String usable_Text;//启用说明

    /*getter setter*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public long getWorkTeamId() {
        return workTeamId;
    }

    public void setWorkTeamId(long workTeamId) {
        this.workTeamId = workTeamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(long organizeId) {
        this.organizeId = organizeId;
    }

    public EnumData.UserType getType() {
        return type;
    }

    public void setType(EnumData.UserType type) {
        this.type = type;
    }

    public String getWorkTeamName() {
        return workTeamName;
    }

    public void setWorkTeamName(String workTeamName) {
        this.workTeamName = workTeamName;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getUsable_Text() {
        return usable_Text;
    }

    public void setUsable_Text(String usable_Text) {
        this.usable_Text = usable_Text;
    }
}
