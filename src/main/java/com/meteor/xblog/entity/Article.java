package com.meteor.xblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    private String id;
    private String fileName;
    private String filePath;
    private String digest;
    private String createTime;
    private Date lastModified;
}
