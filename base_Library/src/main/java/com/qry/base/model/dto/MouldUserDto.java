package com.qry.base.model.dto;

/**
 * className：MouldUserDto
 * author：RonQian
 * created by：2019/1/7 15:15
 * update by：2019/1/7 15:15
 * 修改备注：
 */
public class MouldUserDto {
    private long id;
    private String no;//编号，也是登陆用的帐号
    private String name;//姓名
    private String pwd;//密码
    private String department;//部门
    private String role;//角色
    private String token;//"登陆成功之后返回的USER中会有值 ，在以后的请求的HEADER.Authorization中带上此值")

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "MouldUserDto{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
