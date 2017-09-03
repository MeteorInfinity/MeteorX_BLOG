package com.meteor.xblog.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
class HelloWorldController {

    @RequestMapping("/")
    //@ResponseBody
    public String hello(){
        return "hello";
    }
}
