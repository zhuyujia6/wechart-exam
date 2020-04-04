package com.zyj.wechartexam.weixin.utils;

public class WeiXinBasicKey {
    /**请填写'/'加项目名加'/'或者只填'/'*/
    public final static String APPNAME="/";
    /*	*在验证服务器的请求是否来自己微信的时候，需要将从服务器 获取的timestamp，nonce，token三个参数进行字典排序
    并且加密处理，然后与其接收到的signature进行对比，相同即表明该请求来自微信服务器*/
    public  static final String  TOKEN="zhuyujia6";
    /** 获取凭证的请求链接，其中的APPID和APPSECRET是要去替换的*/
    public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**支持者账号*/

    public final static String APPID="wxd41108ee397cf80f";//zyj

    /**支持者密码*/

    public final static String APPSECRET="1090a1f02bfc962ebdffee46988ac916";//zyj

    /**支持者域名*/

    public final static String SERVERURL="http://zhangmengting.xyz";//zyj

    /**定时获取的ACCESSTOKEN存在这个变量中*/
    public static  String ACCESSTOKEN=null;

    /**获取用户基本信息的请求接口*/
    public final  static String GETUSERBASEINFO="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**图片上传的文件夹*/
    public final static String UPLOADPIC="uploadPic";

    //添加菜单接口
    public static final String ADD_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
    public static final String UPLOAD_URL ="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    /**使用这个接口之前进行网页授权跳转链接微信服务器会给我们一个code，通过code去使用这个接口获取进一步的信息，*/
    public final static String GETACCESS_TOKENBYCODE="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static final String UPLOAD_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    public static final String GETALLOPENID = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";


    /**
     * 关注二维码连接
     */
    public static final String GuanZhuMa="http://weixin.qq.com/r/GS2ehszEjZHyrYjl93gL";
    /**
     * 二维码存放地址
     */
    public static final String SaveQRCodePath="D:/IDEAA/wechartexam/src/main//resources/static/asserts/images/qrcode/";
    /**
     * 二维码logo地址
     */
    public static final String QRCodeLOGOPath="D:/IDEAA/wechartexam/src/main/resources/static/hg.png";
}
