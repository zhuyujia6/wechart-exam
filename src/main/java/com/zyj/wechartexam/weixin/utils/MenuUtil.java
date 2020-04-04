package com.zyj.wechartexam.weixin.utils;

import com.zyj.wechartexam.weixin.bean.memu.*;

public class MenuUtil {

    /**
     * 组装菜单
     * @return
     */
    public static Menu initMenu(){
        Menu menu = new Menu();
        ViewButton button11 = new ViewButton();
        //注意按钮名字不要太长，不然会报40018错误
        button11.setName("学校官网");
        button11.setType("view");
        button11.setUrl("http://www.hnit.edu.cn/");
        //注意链接不要少了https://  否则会报错40055

        SendPicButton button21 = new SendPicButton();
        button21.setName("发图");
        button21.setType("pic_photo_or_album");
        button21.setKey("pic");

        SendLocalButton button32 = new SendLocalButton();
        button32.setName("发位置");
        button32.setType("location_select");
        button32.setKey("local");

        ClickButton button31 = new ClickButton();
        button31.setName("点赞");
        button31.setType("click");
        button31.setKey("strtest");//事件key

        Button button = new Button();
        button.setName("click2");
        button.setSub_button(new Button[]{button31,button32});
        button.setSub_button(new Button[]{button31,button32});

        menu.setButton(new Button[]{button11,button21,button});
        return menu;
    }

}
