package com.meteor.xblog.service;

import com.meteor.xblog.entity.Article;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Meteor on 2017/9/7.
 */

@Service
public class FileService {

    public String getCreateTime(String filePath){
        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + filePath
                    + "/tc" );
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null){
                if(line.endsWith(".md")){
                    strTime = line.substring(0,17);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    public Date getModifiedTime(String filePath){
        File file = new File(filePath);
        Calendar cal = Calendar.getInstance();
        long time = file.lastModified();
        cal.setTimeInMillis(time);
        return cal.getTime();
    }

    public List<String> readFileByLines(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        List<String> lineList = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                lineList.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return lineList;
    }

    public List<Article> scanFolder(String path) {
        List<Article> articleList = new ArrayList<Article>();
        File floder = new File(path);
        String[] fileList = floder.list();

        assert fileList != null;
        for (String aFileList : fileList) {
            File file = new File(path + "\\" + aFileList);

            Article article = new Article();
            article.setFileName(file.getName());
            article.setFilePath(file.getPath());
            article.setCreateTime(getCreateTime(path + "\\" + aFileList));
            article.setLastModified(getModifiedTime(path + "\\" + aFileList));
            articleList.add(article);
        }
        return articleList;
    }
}
