package com.zyj.wechartexam.webCtr.mapper;

import com.zyj.wechartexam.webCtr.bean.ExamPlan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ExamPlanMapper {

    //分页查询
    @Select(value = {"<script>" +
            "select * from examPlan" +
            "<where> 1=1" +
            "<if test=\"planId != null \"> and planId=#{planId} </if>" +
            "<if test=\"planSchool != null \"> and planSchool=#{planSchool} </if>" +
            "<if test=\"planName != null \"> and planName=#{planName} </if>" +
            "<if test=\"planType != null \"> and planType=#{planType} </if>" +
            "<if test=\"planStatue != null \"> and planStatue=#{planStatue} </if>" +
            "<if test=\"planCreatTime != null \"> and planCreatTime=#{planCreatTime} </if>" +
            "</where>" +
            " LIMIT #{startRow},#{endRow} " +
            " </script>"})
    public List<ExamPlan> findExamPlan(@Param("startRow") Integer startRow, @Param("endRow")Integer endRow, @Param("planId") Integer planId, @Param("planSchool")String planSchool, @Param("planName")String planName, @Param("planType")String planType,@Param("planStatue")Integer planStatue,@Param("planCreatTime")String planCreatTime);

    @Select(value = {"<script>" +
            "select count(*) from examPlan" +
            "<where> 1=1" +
            "<if test=\"planId != null \"> and planId=#{planId} </if>" +
            "<if test=\"planSchool != null \"> and planSchool=#{planSchool} </if>" +
            "<if test=\"planName != null \"> and planName=#{planName} </if>" +
            "<if test=\"planType != null \"> and planType=#{planType} </if>" +
            "<if test=\"planStatue != null \"> and planStatue=#{planStatue} </if>" +
            "<if test=\"planCreatTime != null \"> and planCreatTime=#{planCreatTime} </if>" +
            "</where>" +
            " </script>"})
    public Integer findAllCount(@Param("planId") Integer planId, @Param("planSchool")String planSchool, @Param("planName")String planName, @Param("planType")String planType,@Param("planStatue")Integer planStatue,@Param("planCreatTime")String planCreatTime);

    @Insert(value = {
            "<script>" +
                    "insert into examPlan(planSchool, planName, planType,planStatue,planCreatTime) values " +
                    "<foreach collection='examPlanList' item='item' index='index' separator=','>" +
                    "(#{item.planSchool}, #{item.planName}, #{item.planType} ,#{item.planStatue},#{item.planCreatTime})" +
                    "</foreach>" +
                    "</script>"
    })
    public boolean insertExamPlan(@Param(value = "examPlanList") List<ExamPlan> examPlanList);

    //不分页查询
    @Select(value = {"<script>" +
            "select * from examPlan" +
            "<where> 1=1" +
            "<if test=\"planId != null \"> and planId=#{planId} </if>" +
            "<if test=\"planSchool != null \"> and planSchool=#{planSchool} </if>" +
            "<if test=\"planName != null \"> and planName=#{planName} </if>" +
            "<if test=\"planType != null \"> and planType=#{planType} </if>" +
            "<if test=\"planStatue != null \"> and planStatue=#{planStatue} </if>" +
            "<if test=\"planCreatTime != null \"> and planCreatTime=#{planCreatTime} </if>" +
            "</where>" +
            " </script>"})
    public List<ExamPlan> selectExamPlan(@Param("planId") Integer planId, @Param("planSchool")String planSchool, @Param("planName")String planName, @Param("planType")String planType,@Param("planStatue")Integer planStatue,@Param("planCreatTime")String planCreatTime);


    @Update(value = {
            "<script>" +
                    "update examPlan set " +
                    " planId = #{examPlan.planId} ," +
                    " planSchool = #{examPlan.planSchool} ," +
                    " planName = #{examPlan.planName} ," +
                    " planType = #{examPlan.planType} , " +
                    " planStatue = #{examPlan.planStatue}, " +
                    " planCreatTime = #{examPlan.planCreatTime} "+
                    " where planId= #{examPlan.planId}" +
                    "</script>"
    })
    public boolean updateExamPlan(@Param("examPlan") ExamPlan examPlan);

    //按年份查询
    @Select(value = {"<script>" +
            " select * from examPlan " +
            "<where> " +
            " planName like '${year}%' " +
            "</where>" +
            " LIMIT #{startRow},#{endRow} " +
            "</script>"})
    public List<ExamPlan> selectExamPlanByYear(@Param("startRow") Integer startRow, @Param("endRow")Integer endRow,@Param("year") String year);


    @Select(value = {"<script>"  +
            " select count(*) from examPlan "  +
            " <where> "  +
            "  planName like '${year}%' "  +
            " </where>" +
            "</script>"})
    public Integer findAllCountByYear(@Param("year") String year);

}
