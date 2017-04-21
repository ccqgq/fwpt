package com.qgq.fwpt.openaccount.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class StuCou implements Serializable {
    private Integer id;

    private Integer stuId;

    private Integer couId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }
}