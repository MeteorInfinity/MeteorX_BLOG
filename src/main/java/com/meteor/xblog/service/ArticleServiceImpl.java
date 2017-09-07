package com.meteor.xblog.service;

import com.meteor.xblog.entity.Article;
import com.meteor.xblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Meteor on 2017/9/7.
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private FileService fileService;

    public void UpdateArticleMes(String path){
        List<Article> articleList = fileService.scanFolder(path);
        for (Article article : articleList){
            Query query=new Query(Criteria.where("fileName").is(article.getFileName()));
            if(!mongoTemplate.exists(query, article.getClass()))
                articleRepository.save(article);
            else {
                Update update = new Update().set("createTime", article.getCreateTime()).set("lastModified", article.getLastModified());
                mongoTemplate.updateFirst(query, update, article.getClass());
            }
        }
    }

    public void SaveArticleMes(String path){
        List<Article> articleList = fileService.scanFolder(path);
        articleRepository.save(articleList);
    }
}
