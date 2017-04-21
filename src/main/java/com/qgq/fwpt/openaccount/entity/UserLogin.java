package com.qgq.fwpt.openaccount.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class UserLogin implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 账号
     */
    private String number;

    /**
     * 密码
     */
    private String password;

    /**
     * 关联的用户id
     */
    private Integer openId;

    /**
     * 用户的角色（0：学生    1：教师    2：管理员）
     */
    private Integer resource;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }
}