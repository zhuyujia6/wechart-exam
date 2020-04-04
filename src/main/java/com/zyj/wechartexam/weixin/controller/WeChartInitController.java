package com.zyj.wechartexam.weixin.controller;



import com.zyj.wechartexam.weixin.service.MenuService;
import com.zyj.wechartexam.weixin.service.MessageService;

import com.zyj.wechartexam.weixin.utils.CheckUtil;

import com.zyj.wechartexam.weixin.utils.QRCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * 类名称: WeChartInitController
 * 类描述: 与微信对接登陆验证
 */

@Controller
public class WeChartInitController {
    Logger logger= LoggerFactory.getLogger(WeChartInitController.class);

    @Autowired
    MenuService menuService;
    @Autowired
    MessageService messageService;
    @GetMapping(value = "/wx")
    public void login(HttpServletRequest request, HttpServletResponse response){
        logger.info("对接微信成功");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        boolean b = menuService.menuAdd();
        if (b) {
            logger.info("创建菜单成功");
        }
        try {
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature, timestamp, nonce)){
                out.write(echostr);
            }
        } catch (IOException e) {
            logger.info("微信对接错误"+e.getMessage());
            ;
        }finally{
            out.close();
        }
    }

    /**
     * 微信信息处理
     */
    @RequestMapping (value = "/wx",method = RequestMethod.POST)
    @ResponseBody
    public String AutoGraphPostMethod(HttpServletRequest request,String signature,String timestamp,String nonce,String echostr){

        logger.debug(    signature+"  "+timestamp+"  "+nonce);
        if(   CheckUtil.checkSignature(signature, timestamp, nonce)){//请求校验
            String respXml=messageService.processRequest(request);//请求处理
            logger.debug(respXml);
            return respXml;//响应消息
        }

        return null;

    }

    /*@Autowired
    CacheUtil cacheUtil;

    @Autowired
    HttpsRequestUtil httpsRequestUtil;
    @RequestMapping("/access")
    public String getToken(){
        Token access= httpsRequestUtil.getToken();
        System.out.println("=================="+access.toString());
        //31_bUyYrfiMJW8hTrRmfef2qCbK05lI9EmpoHUZB87gQBxBMwP4ghvZmoeWEse7EzE22BAUA0G5eqSMlLxbzSbWsLlzH2CWBRIWFEpiOFPb3p8bLXLnhHPKaHK32B7L-F4kwvGNwyAjho0yOMj_DNUfAEAHRY
        logger.info(access.getAccessToken());
        return "index";
    }*/


   /* *
     * 添加微信公众号菜单
     * @return
    @Autowired
    MenuService menuService;
    @ResponseBody
    @RequestMapping(value="/menuAdd")
    public String menuAdd(){
        boolean b = menuService.menuAdd();
        if (b) {
            return "success";
        }
        return "unsuccess";
    }*/


}
