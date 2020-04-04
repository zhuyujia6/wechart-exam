package com.zyj.wechartexam.webCtr.bean;

import java.io.Serializable;
import java.util.Date;

public class ExamPlan implements Serializable {

    private static final long serialVersionUID = -6543156251508818232L;
    //考试计划编号
    private Integer planId;
    //考试所属学校
    private String planSchool;
    //考试名称
    private String planName;
    //考试类型
    private String planType;
    //创建时间
    private String planCreatTime;
    //状态（0：已销毁；1：待考 2:已考）
    private Integer planStatue;

    public String getPlanCreatTime() {
        return planCreatTime;
    }

    public void setPlanCreatTime(String planCreatTime) {
        this.planCreatTime = planCreatTime;
    }



    public Integer getPlanStatue() {
        return planStatue;
    }

    public void setPlanStatue(Integer planStatue) {
        this.planStatue = planStatue;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanSchool() {
        return planSchool;
    }

    public void setPlanSchool(String planSchool) {
        this.planSchool = planSchool;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Override
    public String toString() {
        return "ExamPlan{" +
                "planId=" + planId +
                ", planSchool='" + planSchool + '\'' +
                ", planName='" + planName + '\'' +
                ", planType='" + planType + '\'' +
                ", planCreatTime=" + planCreatTime +
                ", planStatue=" + planStatue +
                '}';
    }
}
