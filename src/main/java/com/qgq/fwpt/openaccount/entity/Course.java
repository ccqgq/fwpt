package com.qgq.fwpt.openaccount.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Course implements Serializable {
    private Integer id;

    /**
     * 课程名称
     */
    private String couName;

    /**
     * 上课时间
     */
    private String date;

    /**
     * 教师id
     */
    private Integer teaId;

    /**
     * 上课教师
     */
    private String taeName;

    /**
     * 开设学院
     */
    private String college;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 课程状态（0：审核中  1：通过 2：不通过）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTaeName() {
        return taeName;
    }

    public void setTaeName(String taeName) {
        this.taeName = taeName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}