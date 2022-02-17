package com.gzmu.lpzyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* 实现页面跳转
* */
@Controller
public class SkipPagesController {
    //首页
    @RequestMapping("/index")
    public String skipIndex(){
        return "index";
    }

    //数据采集
    @RequestMapping("/getData")
    public String skipData(){
        return "subwayMapShow";
    }

    //地铁网图
    @RequestMapping("/network")
    public String skipNetword(){
        return "network";
    }

    //注册
    @RequestMapping("/to_register")
    public String to_register(){
        return "register";
    }
}
