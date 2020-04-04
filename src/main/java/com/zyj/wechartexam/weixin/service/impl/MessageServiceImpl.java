package com.zyj.wechartexam.weixin.service.impl;

import com.zyj.wechartexam.webCtr.service.UserService;
import com.zyj.wechartexam.weixin.message.responseMessage.ResponseTextMessage;
import com.zyj.wechartexam.weixin.service.MessageService;
import com.zyj.wechartexam.weixin.utils.HttpsRequestUtil;
import com.zyj.wechartexam.weixin.utils.MessageUtil;
import com.zyj.wechartexam.weixin.utils.WeiXinBasicKey;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    UserService userService;
    Logger logger= LoggerFactory.getLogger(MessageServiceImpl.class);
    public static String openId="";
    @Override
    public String processRequest(HttpServletRequest request) {
        String respXml = null;
        String respContent = null;
        try {
            //调用解析微信服务器发过来的请求
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            final String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            //String createTime=requestMap.get("CreateTime");
            String msgType = requestMap.get("MsgType");
            //消息id
            //String msgId= requestMap.get("MsgId");

            openId=fromUserName;//这个是用来绑定微信与学员信息的时候学员微信的openid
            logger.debug(msgType);
            //从消息的类型来分发消息的处理
            if(msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_TEXT)){
                //String content=requestMap.get("Content");//接收的文本
                // 回复文本消息
                ResponseTextMessage textMessage = new ResponseTextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(MessageUtil.RESPONSE_MESSAGE_TYPE_TEXT);
                respContent="您发送的是文本消息";
                //TODO  文本消息的回复
                textMessage.setContent(respContent);
                respXml = MessageUtil.messageToXml(textMessage);

                //TODO  处理文本消息具体请求，前面的是测试
            }else if(msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_LINK)){

                //String title=requestMap.get("Title");

                //	String description=requestMap.get("Description");

                //	String url=requestMap.get("Url");

                //TODO  处理链接消息请求
            }else if(msgType.equals(MessageUtil.RESPONSE_MESSAGE_TYPE_IMAGE)){

                //String picUrl=requestMap.get("PicUrl");
                //TODO  图片消息处理
            }else if(msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_VOICE)){
                //媒体文件标识
                //String mediaId=requestMap.get("MediaId");
                //文件格式
                //String format=requestMap.get("Format");
                //TODO 处理语音消息请求
            }else if(msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_VIDEO)){
                //媒体文件标识
                //	String mediaId=requestMap.get("MediaId");
                //视频消息略缩图的媒体id
                //String thumbMediaId=requestMap.get("ThumbMediaId");
                //TODO  处理视频消息请求
            }else if(msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_LOCATION)){
                //String location_x=requestMap.get("Location_x");
                //String location_y=requestMap.get("Location_y");
                //	String scale=requestMap.get("Scale");
                //String label=requestMap.get("Label");
                //TODO  地理消息请求处理
            }else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_EVENT)) {
                String eventType=requestMap.get("Event");
                //订阅事件或关注事件
                logger.info("点击事件的具体类型"+eventType);
                if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)){
                    ResponseTextMessage textMessage = new ResponseTextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESPONSE_MESSAGE_TYPE_TEXT);
                    logger.info(textMessage.toString());
                    respContent="欢迎关注，<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd41108ee397cf80f&redirect_uri=http%3a%2f%2fzhangmengting.xyz%2fmodifyUserInfo&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\">请点击链接完善身份信息</a>";
                    //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd41108ee397cf80f&redirect_uri=http://zhuyujia6.ticp.io/weixin_exam/weixin/wanshan.do&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
					/*List<ResponseArticle> articles=new ArrayList<ResponseArticle>();
					articles=coreServiceMapper.selectArticles();
					//调用图文方法回复用户图文信息
					ResponseArticleMessage responseArticleMessage = wrapArticleMessage(articles,fromUserName, toUserName);
					//把图文信息转换成xml格式
					respXml=MessageUtil.messageToXml(responseArticleMessage);
					//新 启动一个线程去处理关注的用户信息添加
					Thread t=new Thread(new Runnable() {
						public void run() {
							try {
								JSONObject userBaseInfo = WeiXinCommonUtil.httpsRequest(WeixinbasicKey.GETUSERBASEINFO.replace("ACCESS_TOKEN", WeixinbasicKey.ACCESSTOKEN).replace("OPENID", openId), "GET", null);
								WeixinUserBaseInfo jb = (WeixinUserBaseInfo)JSONObject.toBean(userBaseInfo,WeixinUserBaseInfo.class);
								if(weixinUserInfoMapper.selectUserInfo(jb.getOpenid())>0){//查询用户是否关注过
									if(weixinUserInfoMapper.updateUserInfo(jb.getOpenid())==0){
										YcUtils.logger.debug(jb+"用户信息存储失败");
										userBaseInfo=null;
									}
								}else{
									int result=weixinUserInfoMapper.insertUserInfo(jb);
									if(result==0){
										YcUtils.logger.debug(jb+"用户信息存储失败");
										userBaseInfo=null;
									}
								}
							} catch (Exception e) {
								YcUtils.logger.error(e.getMessage());
								e.printStackTrace();
							}
						}
					});
					t.start();*/
                }else if(MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)){
                    logger.info("取消关注");
                    userService.updateUserType(0,openId);
                   // if(coreServiceMapper.updateSubStatus(fromUserName)==0){

                    //}
                }else if(MessageUtil.EVENT_TYPE_CLICK.equals(eventType)){
                    //TODO  点击事件
                }else if(MessageUtil.EVENT_TYPE_LOCATION.equals(eventType)){
                    //TODO 地理事件
                }
                //TODO    事件类型 具体处理可以见书97页
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return respXml;
    }

    @Override
    public String getOpenid(String code) throws IOException {
        /*通过code获取用户openid*/
        //public static JSONObject getOpenid(String code) throws IOException{

        JSONObject jsonObject = null;
        logger.info(code);
        String url = WeiXinBasicKey.GETACCESS_TOKENBYCODE.replace("APPID", WeiXinBasicKey.APPID).replace("SECRET", WeiXinBasicKey.APPSECRET).replace("CODE", code);
        logger.info(url);
        jsonObject=HttpsRequestUtil.httpsRequest(url,"POST",null);
       /* StringBuffer buffer = new StringBuffer();
        URL url = new URL(path);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
        httpUrlConn.setRequestMethod("POST");
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setUseCaches(false);
        // 将返回的输入流转换成字符串
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        inputStream = null;
        httpUrlConn.disconnect();*/
        String openid=jsonObject.getString("openid");
        return openid;
    }
}
