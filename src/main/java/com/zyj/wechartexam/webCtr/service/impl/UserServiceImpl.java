package com.zyj.wechartexam.webCtr.service.impl;

import com.zyj.wechartexam.webCtr.bean.Users;
import com.zyj.wechartexam.webCtr.mapper.UserMapper;
import com.zyj.wechartexam.webCtr.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserMapper userMapper;



    @Override
    public boolean insertUser(Users user) {
        List<Users> list=new ArrayList<>();
        list.add(user);

        return userMapper.insertUser(list);
    }
    @Override
    public boolean updateUserType(Integer userType,String userOpenid) {

        return userMapper.updateUserType(userType,userOpenid);
    }

    @Override
    public Map<String, Object> getUserPage(Integer limit, Integer offset,Users user) {
        Map<String,Object> resultMap=new HashMap<>();

        List<Users> userList =null;
        Integer total=0;
        try{
            userList=userMapper.findUser(offset,limit,user.getUserId(),user.getUserName(),user.getUserNumber(),user.getUserType(),user.getUserTel(),user.getUserOpenid());

            total=userMapper.findAllCount(user.getUserId(),user.getUserName(),user.getUserNumber(),user.getUserType(),user.getUserTel(),user.getUserOpenid());
        }catch (Exception e){
            logger.info("查询用户列表失败"+e.getMessage());
        }
        resultMap.put("data",userList);
        resultMap.put("total",total);
        return resultMap;
    }

    @Override
    public boolean updateUser(Users user) {
        return userMapper.updateUser(user);
    }

    /**
     * 多条件查询用户
     * @param user
     * @return*/
    @Override
    public List<Users> selectUser(Users user) {
        return userMapper.selectUser(user.getUserId(),user.getUserName(),user.getUserNumber(),user.getUserType(),user.getUserTel(),user.getUserOpenid());
    }

    @Override
    public boolean deleteUser(Users user) {
        return userMapper.deleteUser(user.getUserId());
    }
}
