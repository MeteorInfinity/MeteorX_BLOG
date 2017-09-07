package com.meteor.xblog.controller;

import com.meteor.xblog.entity.Article;
import com.meteor.xblog.service.FileService;
import com.meteor.xblog.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@EnableAutoConfiguration
@RequestMapping("/Test")
public class TestController {

    @Autowired
    private MarkdownService markdownService;
    @Autowired
    private FileService fileService;

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
        String createTime = fileService.getCreateTime(filePath);
        String modifiedTime = fileService.getModifiedTime(filePath).toLocaleString();
        return "创建时间 : " + createTime + " + 修改时间 : " + modifiedTime;
    }

    @RequestMapping("/scan")
    @ResponseBody
    public List<Article> testScanFile(){
        String path = System.getProperty("user.dir") + "\\md\\";
        return fileService.scanFolder(path);
    }

    @RequestMapping("/mdRead")
    @ResponseBody
    public String testMdRead(){
        String filePath = System.getProperty("user.dir") + "\\md\\testMarkdown.md";
        List<String> lineList = fileService.readFileByLines(filePath);
        StringBuilder html = new StringBuilder();
        for(String line : lineList){
            html.append(markdownService.markdown(line)).append("\n");
        }
        return html.toString();
    }

    @RequestMapping("/md")
    @ResponseBody
    public String testMarkdown(){
        return markdownService.multiMarkdown("This is *Sparta*");
    }
}
