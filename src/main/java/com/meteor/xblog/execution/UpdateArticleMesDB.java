package com.meteor.xblog.execution;

import com.meteor.xblog.entity.Article;
import com.meteor.xblog.repository.ArticleRepository;
import com.meteor.xblog.service.DirctoryServiceWork;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class UpdateArticleMesDB {
    private DirctoryServiceWork dirctoryServiceWork;
    private ArticleRepository articleRepository;
    private MongoTemplate mongoTemplate;

    public void updateArticleMes(){
        String path = System.getProperty("user.dir") + "\\md\\";
        List<Article> articleList = dirctoryServiceWork.scanFolder(path);
        for (Article article : articleList){
            Query query=new Query(Criteria.where("fileName").is(article.getFileName()));
            if(mongoTemplate.exists(query, article.getClass()))
                articleRepository.save(article);
            else {
                Update update = new Update().set("createTime", article.getCreateTime()).set("lastModified", article.getLastModified());
                mongoTemplate.updateFirst(query, update, article.getClass());
            }
        }
    }
}
