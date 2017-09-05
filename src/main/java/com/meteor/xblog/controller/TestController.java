package com.meteor.xblog.controller;

import com.meteor.xblog.service.DirctoryService;
import com.meteor.xblog.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/scan")
    @ResponseBody
    public List<String> testScan(){
        String path = System.getProperty("user.dir") + "\\md\\";
        List<String> fileList = new ArrayList<String>();
        try {
            fileList = dirctoryService.scan(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @RequestMapping("/scanFile")
    @ResponseBody
    public List<String> testScanFile(){
        String path = System.getProperty("user.dir") + "\\md\\";
        List<String> fileList = new ArrayList<String>();
        List<String> fileMesList = new ArrayList<String>();
        String createTime,modifiedTime;
        try {
            fileList = dirctoryService.scan(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String fileStr : fileList){
            createTime = dirctoryService.getCreateTime(fileStr);
            modifiedTime = dirctoryService.getModifiedTime(fileStr);
            fileMesList.add(fileStr + " CreateTime:" + createTime + " ModifiedTime:" + modifiedTime);
        }
        return fileMesList;
    }

    @RequestMapping("/mdRead")
    @ResponseBody
    public String testMdRead(){
        String filePath = System.getProperty("user.dir") + "\\md\\testMarkdown.md";
        List<String> lineList = dirctoryService.readFileByLines(filePath);
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
