package com.meteor.xblog.repository;

import com.meteor.xblog.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, Integer> {
    Article findByFileName(String fileName);
}
