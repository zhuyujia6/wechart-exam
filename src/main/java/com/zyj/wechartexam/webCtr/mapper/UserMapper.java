package com.zyj.wechartexam.webCtr.mapper;

import com.zyj.wechartexam.webCtr.bean.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select(value = {"<script>" +
            "select * from users" +
            "<where> 1=1" +
            "<if test=\"userId != null \"> and userId=#{userId} </if>" +
            "<if test=\"userName != null \"> and userName=#{userName} </if>" +
            "<if test=\"userNumber != null \"> and userNumber=#{userNumber} </if>" +
            "<if test=\"userType != null \"> and userType=#{userType} </if>" +
            "<if test=\"userTel != null \"> and userTel=#{userTel} </if>" +
            "<if test=\"userOpenid != null \"> and userOpenid=#{userOpenid} </if>" +
            "</where>" +
            " LIMIT #{startRow},#{endRow} " +
            " </script>"})
    public List<Users> findUser(@Param("startRow") Integer startRow,@Param("endRow")Integer endRow,@Param("userId") Integer userId,@Param("userName")String userName,@Param("userNumber")String userNumber,@Param("userType")Integer userType,@Param("userTel")String userTel,@Param("userOpenid")String userOpenid);


    //不分页查询
    @Select(value = {"<script>" +
            "select * from users" +
            "<where> 1=1" +
            "<if test=\"userId != null \"> and userId=#{userId} </if>" +
            "<if test=\"userName != null \"> and userName=#{userName} </if>" +
            "<if test=\"userNumber != null \"> and userNumber=#{userNumber} </if>" +
            "<if test=\"userType != null \"> and userType=#{userType} </if>" +
            "<if test=\"userTel != null \"> and userTel=#{userTel} </if>" +
            "<if test=\"userOpenid != null \"> and userOpenid=#{userOpenid} </if>" +
            "</where>" +
            " </script>"})
    public List<Users> selectUser(@Param("userId") Integer userId,@Param("userName")String userName,@Param("userNumber")String userNumber,@Param("userType")Integer userType,@Param("userTel")String userTel,@Param("userOpenid")String userOpenid);

    @Select(value = {"<script>" +
            "select count(*) from users" +
            "<where> 1=1" +
            "<if test=\"userId != null \"> and userId=#{userId} </if>" +
            "<if test=\"userName != null \"> and userName=#{userName} </if>" +
            "<if test=\"userNumber != null \"> and userNumber=#{userNumber} </if>" +
            "<if test=\"userType != null \"> and userType=#{userType} </if>" +
            "<if test=\"userTel != null \"> and userTel=#{userTel} </if>" +
            "<if test=\"userOpenid != null \"> and userOpenid=#{userOpenid} </if>" +
            "</where>" +
            " </script>"})
    public Integer findAllCount(@Param("userId") Integer userId,@Param("userName")String userName,@Param("userNumber")String userNumber,@Param("userType")Integer userType,@Param("userTel")String userTel,@Param("userOpenid")String userOpenid);

    @Insert(value = {
            "<script>" +
            "insert into users(userName, userSex, userTel,userPassword,userType,userOpenid,userNumber) values " +
            "<foreach collection='usersList' item='item' index='index' separator=','>" +
            "(#{item.userName}, #{item.userSex}, #{item.userTel} ,#{item.userPassword},#{item.userType},#{item.userOpenid},#{item.userNumber})" +
            "</foreach>" +
            "</script>"
    })
    public boolean insertUser(@Param(value = "usersList") List<Users> usersList);

    @Update(value = {
                    "<script>" +
                    "update users set userType = #{userType}" +
                    " where userOpenid= #{userOpenid}" +
                    "</script>"
    })
    public boolean updateUserType(@Param(value = "userType") Integer userType,@Param(value = "userOpenid") String userOpenid);
    public boolean deleteUser(Integer id);

    @Update(value = {
            "<script>" +
                    "update users set " +
                    " userName = #{user.userName} ," +
                    " userSex = #{user.userSex} ," +
                    " userNumber = #{user.userNumber} ," +
                    " userPassword = #{user.userPassword} , " +
                    " userOpenid = #{user.userOpenid} , " +
                    " userTel = #{user.userTel} , " +
                    " userType = #{user.userType} " +
                    " where userId= #{user.userId}" +
                    "</script>"
    })
    public boolean updateUser(@Param("user") Users user);
}
