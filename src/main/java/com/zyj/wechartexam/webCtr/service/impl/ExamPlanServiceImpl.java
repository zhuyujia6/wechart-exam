package com.zyj.wechartexam.webCtr.service.impl;

import com.zyj.wechartexam.webCtr.bean.ExamPlan;
import com.zyj.wechartexam.webCtr.bean.Users;
import com.zyj.wechartexam.webCtr.mapper.ExamPlanMapper;
import com.zyj.wechartexam.webCtr.service.ExamPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamPlanServiceImpl implements ExamPlanService {
    private static Logger logger = LoggerFactory.getLogger(ExamPlanServiceImpl.class);
    @Autowired
    ExamPlanMapper examPlanMapper;

    @Override
    public boolean updateExamPlan(ExamPlan examPlan) {
        return examPlanMapper.updateExamPlan(examPlan);
    }

    @Override
    public Map<String, Object> getUserPage(Integer limit, Integer offset, ExamPlan examPlan ,String year) {
        Map<String, Object> resultMap = new HashMap<>();

        List<ExamPlan> examPlanList = null;
        Integer total = 0;
        if(year==null||year.equals("")){
            try {
                examPlanList = examPlanMapper.findExamPlan(offset, limit, examPlan.getPlanId(), examPlan.getPlanSchool(), examPlan.getPlanName(), examPlan.getPlanType(), examPlan.getPlanStatue(),examPlan.getPlanCreatTime());
                total = examPlanMapper.findAllCount(examPlan.getPlanId(), examPlan.getPlanSchool(), examPlan.getPlanName(), examPlan.getPlanType(), examPlan.getPlanStatue(),examPlan.getPlanCreatTime());
            } catch (Exception e) {
                logger.info("查询用户列表失败" + e.getMessage());
            }
        }else{
            try {
                examPlanList = examPlanMapper.selectExamPlanByYear(offset,limit,year);
                total = examPlanMapper.findAllCountByYear(year);
            } catch (Exception e) {
                logger.info("查询用户列表失败" + e.getMessage());
            }
        }

        resultMap.put("data", examPlanList);
        resultMap.put("total", total);
        return resultMap;
    }

    @Override
    public List<ExamPlan> selectExamPlan(ExamPlan examPlan) {
        return examPlanMapper.selectExamPlan(examPlan.getPlanId(), examPlan.getPlanSchool(), examPlan.getPlanName(), examPlan.getPlanType(), examPlan.getPlanStatue(),examPlan.getPlanCreatTime());
    }

    @Override
    public boolean insertExamPlan(ExamPlan examPlan) {
        List<ExamPlan> list = new ArrayList<>();
        list.add(examPlan);

        return examPlanMapper.insertExamPlan(list);

    }
}