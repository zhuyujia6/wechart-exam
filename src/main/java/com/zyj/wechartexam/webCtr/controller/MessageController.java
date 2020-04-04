package com.zyj.wechartexam.webCtr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MessageController {

    @GetMapping("/textMessage")
    public String getTextMessage(){

        return "message/textmessage";
    }

}
