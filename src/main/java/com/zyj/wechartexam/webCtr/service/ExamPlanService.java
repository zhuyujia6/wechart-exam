package com.zyj.wechartexam.webCtr.service;

import com.zyj.wechartexam.webCtr.bean.ExamPlan;

import java.util.List;
import java.util.Map;

public interface ExamPlanService {
    public Map<String,Object> getUserPage(Integer limit, Integer offset, ExamPlan examPlan,String year);

    public List<ExamPlan> selectExamPlan(ExamPlan examPlan);
    public boolean updateExamPlan(ExamPlan examPlan);

    public boolean insertExamPlan(ExamPlan examPlan);
}
