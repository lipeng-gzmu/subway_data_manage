package com.gzmu.lpzyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkipPagesController {
    @RequestMapping("/index")
    public String skipIndex(){
        return "redirect:/index";
    }
}
