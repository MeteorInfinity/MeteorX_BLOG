package com.meteor.xblog.service;

import org.springframework.stereotype.Repository;

/**
 * Created by Meteor on 2017/9/7.
 */

@Repository
public interface ArticleService{

    public void UpdateArticleMes(String path);

    public void SaveArticleMes(String path);

}
