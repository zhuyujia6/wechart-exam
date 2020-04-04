package com.zyj.wechartexam.webCtr.bean;

import org.springframework.stereotype.Component;


import java.io.Serializable;

@Component
public class Users implements Serializable {
    private static final long serialVersionUID = -2307111104160171760L;

    //用户ID
    private Integer userId;
    //用户姓名(管理员或监考员)
    private String userName;
    //监考员工号
    private String userNumber;
    //用户性别
    private Integer userSex;
    //用户联系方式
    private String userTel;
    //用户密码
    private String userPassword;
    //用户类型（0：取消关注或用户注销1：超级管理员2：普通管理员3：普通用户）
    private Integer userType;
    //用户微信openID
    private  String userOpenid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", userSex=" + userSex +
                ", userTel='" + userTel + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType=" + userType +
                ", userOpenid='" + userOpenid + '\'' +
                '}';
    }
}
