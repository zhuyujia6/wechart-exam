package com.zyj.wechartexam.webCtr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClockController {
    @GetMapping("/clockInfo")
    public String getClockInfo(){
        return "clock/clockinfo";
    }
}
