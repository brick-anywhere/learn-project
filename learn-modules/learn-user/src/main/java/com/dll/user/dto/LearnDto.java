package com.dll.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dll
 * @date 2025-07-08 17:37
 */
@Data
@Accessors(chain = true)
@TableName("learn_user")
public class LearnDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户信息
     */
    private String userInfo;
    /**
     * 课程
     */
    private String lesson;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 删除标志 0未删除 1删除
     */
    private Integer deFlag;


    /**
     * 页数
     */
    @TableField(exist = false)
    private Integer pageNo;
    /**
     * 每页展示条数
     */
    @TableField(exist = false)
    private Integer pageSize;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LearnDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", lesson='" + lesson + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deFlag=" + deFlag +
                '}';
    }

    public LearnDto(Long id, String userName, String userInfo, String lesson, Date createTime, Date updateTime, Integer deFlag) {
        this.id = id;
        this.userName = userName;
        this.userInfo = userInfo;
        this.lesson = lesson;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deFlag = deFlag;
    }
    public LearnDto(){};

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeFlag() {
        return deFlag;
    }

    public void setDeFlag(Integer deFlag) {
        this.deFlag = deFlag;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }



}
