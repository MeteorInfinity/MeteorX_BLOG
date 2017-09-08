package com.meteor.xblog.service;

import com.meteor.xblog.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Meteor on 2017/9/7.
 */

@Repository
public interface ArticleService{

    void UpdateArticle();

    void SaveArticle();

    // List<Article> FindByTime();

    Article FindByFileName(String fileName);

    List<Article> FindAll();

}
