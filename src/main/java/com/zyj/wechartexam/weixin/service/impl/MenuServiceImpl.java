package com.zyj.wechartexam.weixin.service.impl;


import com.zyj.wechartexam.weixin.service.MenuService;
import com.zyj.wechartexam.weixin.utils.HttpsRequestUtil;
import com.zyj.wechartexam.weixin.utils.MenuUtil;
import com.zyj.wechartexam.weixin.utils.WeiXinBasicKey;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    Logger logger= LoggerFactory.getLogger(MenuServiceImpl.class);

    /**
     * 添加自定义菜单
     */
    @Autowired
    HttpsRequestUtil httpsRequestUtil;
    @Override
    public boolean menuAdd() {
        String url = WeiXinBasicKey.ADD_MENU_URL.replace("ACCESS_TOKEN", httpsRequestUtil.getToken().getAccessToken());
        String menu = JSONObject.fromObject(MenuUtil.initMenu()).toString();


        JSONObject httpClientResult=HttpsRequestUtil.httpsRequest(url,"POST",menu);
        if("ok".equals(httpClientResult.getString("errmsg"))){
            logger.info("添加菜单结果：{}", httpClientResult.get("errmsg"));
            return true;
        }
        logger.info("添加菜单结果：{}", httpClientResult);
        return false;

    }


}
