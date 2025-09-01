package com.dll.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author dll
 * @date 2025-07-08 21:55
 */
@Data
@Accessors(chain = true)
public class QueryLearnDto implements Serializable {

    /**
     * 页数
     */
    private Integer pageNo;
    /**
     * 每页展示条数
     */
    private Integer pageSize;
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
     * 创建时间  1
     * 修改时间  2
     */
    private Integer timeFlag;
    /**
     *
     * 从近到远 1
     * 从远到近 2
     */
    private Integer sortStatus;
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
     * 批量性的主键
     */
    private List<Integer> ids;

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

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public Integer getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(Integer timeFlag) {
        this.timeFlag = timeFlag;
    }

    public Integer getSortStatus() {
        return sortStatus;
    }

    public void setSortStatus(Integer sortStatus) {
        this.sortStatus = sortStatus;
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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }


}
