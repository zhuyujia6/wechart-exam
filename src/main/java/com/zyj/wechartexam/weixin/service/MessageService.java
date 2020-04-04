package com.zyj.wechartexam.weixin.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public interface MessageService {
    /**
     * 处理微信服务器发过来的请求
     * @param request
     * @return
     */
    public  String processRequest(HttpServletRequest request);
    /**
     * 获取openid
     * @throws IOException
     */
    public String getOpenid(String code) throws IOException;

}
