package com.qgq.fwpt.openaccount.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Teacher implements Serializable {
    private Integer id;

    /**
     * 教师姓名
     */
    private String teaName;

    /**
     * 职工号
     */
    private String teaNumber;

    /**
     * 所在学院
     */
    private String college;

    /**
     * 职称（1：助教 2：讲师 3：教授）
     */
    private Integer professional;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱账号
     */
    private String email;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaNumber() {
        return teaNumber;
    }

    public void setTeaNumber(String teaNumber) {
        this.teaNumber = teaNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getProfessional() {
        return professional;
    }

    public void setProfessional(Integer professional) {
        this.professional = professional;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}