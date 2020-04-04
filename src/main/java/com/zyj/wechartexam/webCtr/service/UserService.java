package com.zyj.wechartexam.webCtr.service;

import com.zyj.wechartexam.webCtr.bean.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {



   public List<Users> selectUser(Users user);

    public boolean insertUser(Users user);

    public boolean deleteUser(Users user);

    public boolean updateUserType(Integer userType,String userOpenid);

    public boolean updateUser(Users user);

    public Map<String,Object> getUserPage(Integer limit,Integer offset,Users user);
}
