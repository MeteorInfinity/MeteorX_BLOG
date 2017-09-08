package com.meteor.xblog.execution;

import com.meteor.xblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FloderTimedScan {
    private final static long intervalTime = 30 * 60 * 1000;

    @Autowired
    private ArticleService articleService;

    @Scheduled(fixedRate = intervalTime)
    public void fixedRateJob(){
        String path = System.getProperty("user.dir") + "\\md\\";
        articleService.UpdateArticle();
    }
}
