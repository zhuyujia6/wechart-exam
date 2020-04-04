package com.zyj.wechartexam.webCtr.controller;



import com.google.gson.Gson;
import com.zyj.wechartexam.webCtr.bean.Users;
import com.zyj.wechartexam.webCtr.service.UserService;
import com.zyj.wechartexam.weixin.service.MessageService;
import com.zyj.wechartexam.weixin.utils.HttpsRequestUtil;
import com.zyj.wechartexam.weixin.utils.QRCodeUtil;
import com.zyj.wechartexam.weixin.utils.WeiXinBasicKey;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RightsController {
    private static Logger logger= LoggerFactory.getLogger(RightsController.class);
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    HttpsRequestUtil httpsRequestUtil;
    @GetMapping("/getQrCode")
    public String getAttentionQrCode(){
        QRCodeUtil.zxingCodeCreate(WeiXinBasicKey.GuanZhuMa
                , WeiXinBasicKey.SaveQRCodePath+"gzqrcode.jpg",500,WeiXinBasicKey.QRCodeLOGOPath);

        return "rights/printQrCode";
    }

    @RequestMapping("/modifyUserInfo")
    public String getOpenid(String code,Users user, HttpSession session, Model model) throws IOException {
        //System.out.println(codebiz.getOpenid(code));
        String openid=messageService.getOpenid(code);
        session.setAttribute("openid",openid);

        return "modifyUserInfo";
    }
    @RequestMapping (value = "/user/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(Users user,HttpSession httpSession) throws IOException {
        String openid = httpSession.getAttribute("openid").toString();

        user.setUserOpenid(openid);
        logger.info(user.toString());
        List<Users> result= userService.selectUser(user);
        if(!result.isEmpty()) {
            userService.updateUserType(3,user.getUserOpenid());
            return "ok";
        }
        logger.info(openid);
        String url = WeiXinBasicKey.GETUSERBASEINFO.replace("ACCESS_TOKEN",httpsRequestUtil.getToken().getAccessToken()).replace("OPENID",openid);
        JSONObject jsonObject=HttpsRequestUtil.httpsRequest(url,"GET",null);
        Integer sex= jsonObject.getInt("sex");
        user.setUserSex(sex);
        //设置用户类型为普通用户
        user.setUserType(3);
        user.setUserPassword("a");
        logger.info(user.toString());
        boolean userResult=userService.insertUser(user);
        return (userResult==true?"ok":"信息更新失败，请联系管理员");
    }

    /*//查询所有用户（默认查询微信用户）
    @GetMapping("/allUsers")
    public String getAllUsers(Users user,Model model){
        logger.info(user.toString());
        if(user.getUserType()==null){
            user.setUserType(3);
            List<Users> userResult=userService.selectUser(user);
            logger.info(userResult.toString());
            model.addAttribute("AllUsers",userResult);
        }

        return  "rights/allUsers";
    }*/
    //ajax查询用户
    @ResponseBody
   @GetMapping("/getUserPage")
    public Map<String,Object> getUserPage(Users user,@RequestParam(value = "limit",required = true)Integer limit,
                                            @RequestParam(value = "offset",required = true)Integer offset){
       if(user.getUserType()==null){
           user.setUserType(3);
       }
        return userService.getUserPage(limit,offset,user);
     }




    @GetMapping("/findUserByType/{id}")
    public String findUserByType(@PathVariable("id")Integer id,Model model,Users user){

        user.setUserType(id);
        List<Users> userResult=userService.selectUser(user);
        logger.info("userResult:"+userResult);
        model.addAttribute("AllUsers",userResult);
        return  "rights/allUsers";
    }

    //修改
    @PostMapping("/updateUser")
    @ResponseBody
    public Map<String,Object> updateUser(Users user,Model model){
        logger.info("接受的"+user.toString());
        Map<String,Object> resultMap=new HashMap<String, Object>();
        List<Users> reUser=userService.selectUser(user);
        if (!reUser.isEmpty()){
            user.setUserType(reUser.get(0).getUserType());
            user.setUserPassword(reUser.get(0).getUserPassword());
            user.setUserOpenid(reUser.get(0).getUserOpenid());
        }else{
            resultMap.put("state","error");
            return resultMap;
        }
        logger.info(user.toString());
        boolean result=userService.updateUser(user);
        if (result){
            resultMap.put("state","success");
        }else {
            resultMap.put("state","err");
        }
        return resultMap;
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUserById(String ids, Users user){
        //删除用户==把用户类型设置为0
        logger.info("ID===="+ids);
        //删除===修改userType==0
        Map<String,Object> resultMap=new HashMap<String, Object>();
        String[] ss=ids.split(",");
        for (String s : ss){
            user.setUserId(Integer.parseInt(s));
            List<Users> list=userService.selectUser(user);
            if(!list.isEmpty()){
                Users setUser=list.get(0);
                setUser.setUserType(0);
                logger.info("被删除的用户"+setUser.toString());
                boolean result=userService.updateUser(setUser);
                if(result){
                    resultMap.put("type","success");
                    logger.info("ID为"+s+"的用户"+"删除成功");
                }else {
                    resultMap.put("type","err");
                }
            }else {
                logger.error("找不到ID为"+s+"的用户");
                continue;
            }
        }

        return resultMap;
    }
}
