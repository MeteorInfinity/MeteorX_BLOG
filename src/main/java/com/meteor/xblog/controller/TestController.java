package com.meteor.xblog.controller;

import com.meteor.xblog.service.DirctoryService;
import com.meteor.xblog.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/Test")
public class TestController {

    @Autowired
    private MarkdownService markdownService;
    @Autowired
    private DirctoryService dirctoryService;

    @RequestMapping("/dir")
    @ResponseBody
    public String testDir(){
        return System.getProperty("user.dir");
    }

    @RequestMapping("/file")
    @ResponseBody
    public String testFile(){
        String path = System.getProperty("user.dir") + "\\md\\";
        String filePath = path + "test.md";
        String createTime = dirctoryService.getCreateTime(filePath);
        String modifiedTime = dirctoryService.getModifiedTime(filePath);
        return "创建时间 : " + createTime + " + 修改时间 : " + modifiedTime;
    }

    @RequestMapping("/md")
    @ResponseBody
    public String testMarkdown(){
        return markdownService.multiMarkdown("This is *Sparta*");
    }
}
