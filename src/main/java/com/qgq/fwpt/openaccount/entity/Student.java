package com.qgq.fwpt.openaccount.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Student implements Serializable {
    private Integer id;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 学生学号
     */
    private String stuNumber;

    /**
     * 邮箱账号
     */
    private String email;

    /**
     * 学院
     */
    private String college;

    /**
     * 班级
     */
    private String grade;

    /**
     * 状态（0：以注册  1：未注册）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}