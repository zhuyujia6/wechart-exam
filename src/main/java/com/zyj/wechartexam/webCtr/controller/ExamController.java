package com.zyj.wechartexam.webCtr.controller;

import com.zyj.wechartexam.webCtr.bean.ExamPlan;
import com.zyj.wechartexam.webCtr.bean.Users;
import com.zyj.wechartexam.webCtr.service.ExamPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {
    private static Logger logger = LoggerFactory.getLogger(ExamController.class);
    @Autowired
    ExamPlanService examPlanService;

    @GetMapping("/examPlan")
    public String getExamPlan() {
        return "exam/examplan";
    }

    @GetMapping("/examInfo")
    public String getExamInfo() {
        return "exam/examinfo";
    }

    @ResponseBody
    @GetMapping("/getExamPlanList")
    public Map<String, Object> getExamPlanList(ExamPlan examPlan, @RequestParam(value = "limit", required = true) Integer limit,
                                               @RequestParam(value = "offset", required = true) Integer offset,@RequestParam(value = "year") String year) {
        return examPlanService.getUserPage(limit, offset, examPlan,year);
    }

    @PostMapping("/deleteExamPlan")
    @ResponseBody
    public Map<String, Object> deleteExamPlan(String[] ids, ExamPlan examPlan) {
        //删除考试计划==把考试计划状态设置为0
        logger.info("ID====" + ids);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //String[] ss=ids.split(",");
        for (String id : ids) {
            examPlan.setPlanId(Integer.parseInt(id));
            List<ExamPlan> list = examPlanService.selectExamPlan(examPlan);
            if (!list.isEmpty()) {
                ExamPlan setExamPlan = list.get(0);
                setExamPlan.setPlanStatue(0);
                logger.info("被删除的考试计划" + setExamPlan.toString());
                boolean result = examPlanService.updateExamPlan(setExamPlan);
                if (result) {
                    resultMap.put("type", "success");
                    logger.info("ID为" + id + "的考试计划" + "删除成功");
                } else {
                    resultMap.put("type", "err");
                }
            } else {
                logger.error("找不到ID为" + id + "考试计划");
                continue;
            }
        }

        return resultMap;
    }
    //新增
    @ResponseBody
    @PostMapping("/addExamPlan")
    public Map<String, Object> addExamPlan(ExamPlan examPlan) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<ExamPlan> examPlanList=examPlanService.selectExamPlan(examPlan);
        if(!examPlanList.isEmpty()&&examPlanList.get(0).getPlanName().equals(examPlan.getPlanName())&&examPlanList.get(0).getPlanSchool().equals(examPlan.getPlanSchool())&&examPlanList.get(0).getPlanType().equals(examPlan.getPlanType())){
            resultMap.put("state","考试计划已存在，请勿重新添加");
            return resultMap;
        }else{
            Date now=new Date();
            examPlan.setPlanCreatTime(sdf.format(now));
            examPlan.setPlanStatue(1);
            boolean reExamPlan=examPlanService.insertExamPlan(examPlan);
            if(reExamPlan){
                resultMap.put("state","success");
            }else{
                resultMap.put("state","添加失败请重试");
            }
            return resultMap;
        }

    }
    //修改
    @ResponseBody
    @PostMapping("/updateExamPlan")
    public Map<String,Object> updateExamPlan(ExamPlan examPlan){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(examPlan.getPlanStatue()==null){
            examPlan.setPlanStatue(1);
        }
        boolean reExamPlan=examPlanService.updateExamPlan(examPlan);
        if (reExamPlan){
            resultMap.put("state","success");
        }else {
            resultMap.put("state","err");
        }
        return resultMap;
    }
}