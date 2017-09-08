package com.meteor.xblog.controller;

import com.meteor.xblog.entity.Article;
import com.meteor.xblog.service.ArticleService;
import com.meteor.xblog.service.FileService;
import com.meteor.xblog.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Meteor on 2017/9/8.
 */

@Controller
@EnableAutoConfiguration
public class PageController {

    @Autowired
    private MarkdownService markdownService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String Index(Model model){
        articleService.UpdateArticle();
        List<Article> articleList = articleService.FindAll();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @RequestMapping("/article")
    public String Article(Model model, HttpServletRequest request){

        String fileName = request.getParameter("articleName");
        Article article = articleService.FindByFileName(fileName);
        List<String> lineList = fileService.readFileByLines(article.getFilePath());

        StringBuilder articleHTML = new StringBuilder();
        for(String line : lineList) {
            articleHTML.append(markdownService.multiMarkdown(line)).append("\n");
        }

        model.addAttribute("article", article);
        model.addAttribute("articleHTML",articleHTML.toString());

        return "article";
    }

}
