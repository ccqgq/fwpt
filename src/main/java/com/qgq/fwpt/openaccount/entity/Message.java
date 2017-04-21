package com.qgq.fwpt.openaccount.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Message implements Serializable {
    private Integer id;

    /**
     * 信息标题
     */
    private String title;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 信息类型（0：就业信息  1：通知通告）
     */
    private Integer type;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}