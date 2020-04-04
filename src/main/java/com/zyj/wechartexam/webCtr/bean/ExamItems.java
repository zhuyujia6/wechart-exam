package com.zyj.wechartexam.webCtr.bean;

import java.io.Serializable;

public class ExamItems implements Serializable {
    private static final long serialVersionUID = 1442247813700796851L;

    private Integer itemsId;
    //学院
    private String itemsDept;
    //专业
    private String itemsMajor;
    //考试科目
    private String itemsSubject;
    //考试地点
    private String itemsPlace;
    //考试人数
    private String itemsNumber;
    //考试日期
    private String itemsExamDate;
    //考试时间
    private String itemsExamTime;
    //考试类型1：笔试2：机试
    private Integer itemsType;
    //考试状态1：已考2：待考
    private Integer itemsStatue;
    //用户id
    private Integer userId;
    //考试计划id
    private Integer planId;

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsDept() {
        return itemsDept;
    }

    public void setItemsDept(String itemsDept) {
        this.itemsDept = itemsDept;
    }

    public String getItemsMajor() {
        return itemsMajor;
    }

    public void setItemsMajor(String itemsMajor) {
        this.itemsMajor = itemsMajor;
    }

    public String getItemsSubject() {
        return itemsSubject;
    }

    public void setItemsSubject(String itemsSubject) {
        this.itemsSubject = itemsSubject;
    }

    public String getItemsPlace() {
        return itemsPlace;
    }

    public void setItemsPlace(String itemsPlace) {
        this.itemsPlace = itemsPlace;
    }

    public String getItemsNumber() {
        return itemsNumber;
    }

    public void setItemsNumber(String itemsNumber) {
        this.itemsNumber = itemsNumber;
    }

    public String getItemsExamDate() {
        return itemsExamDate;
    }

    public void setItemsExamDate(String itemsExamDate) {
        this.itemsExamDate = itemsExamDate;
    }

    public String getItemsExamTime() {
        return itemsExamTime;
    }

    public void setItemsExamTime(String itemsExamTime) {
        this.itemsExamTime = itemsExamTime;
    }

    public Integer getItemsType() {
        return itemsType;
    }

    public void setItemsType(Integer itemsType) {
        this.itemsType = itemsType;
    }

    public Integer getItemsStatue() {
        return itemsStatue;
    }

    public void setItemsStatue(Integer itemsStatue) {
        this.itemsStatue = itemsStatue;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "ExamItems{" +
                "itemsId=" + itemsId +
                ", itemsDept='" + itemsDept + '\'' +
                ", itemsMajor='" + itemsMajor + '\'' +
                ", itemsSubject='" + itemsSubject + '\'' +
                ", itemsPlace='" + itemsPlace + '\'' +
                ", itemsNumber='" + itemsNumber + '\'' +
                ", itemsExamDate='" + itemsExamDate + '\'' +
                ", itemsExamTime='" + itemsExamTime + '\'' +
                ", itemsType=" + itemsType +
                ", itemsStatue=" + itemsStatue +
                ", userId=" + userId +
                ", planId=" + planId +
                '}';
    }
}
