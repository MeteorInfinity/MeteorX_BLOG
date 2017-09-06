package com.meteor.xblog.execution;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class floderTimedScan {
    private final static long intervalTime = 30 * 60 * 1000;
    private UpdateArticleMesDB updateArticleMesDB;

    @Scheduled(fixedRate = intervalTime)
    public void fixedRateJob(){
        updateArticleMesDB.updateArticleMes();
    }
}
