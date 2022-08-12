package com.jasoncode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping
    public String getindex(){
        return "index";
    }

    @RequestMapping("/boardedit")
    public String getboardedit(){
        return "boardedit";
    }
}
