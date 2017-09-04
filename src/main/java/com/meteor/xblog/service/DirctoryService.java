package com.meteor.xblog.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DirctoryService {

    private List<String> filePathList;

    public List<String> scan(String path) throws IOException {
        filePathList = new ArrayList<String>();
        scanFolder(path);
        return filePathList;
    }

    private void scanFolder(String path) throws FileNotFoundException, IOException {

        try {
            File file = new File(path);

            if (!file.isDirectory())
                filePathList.add(file.getPath());
            else {
                String[] filelist = file.list();

                for (int i = 0; i < filelist.length; i++) {
                    File floderFile = new File(path + "\\" + filelist[i]);

                    if (!floderFile.isDirectory())
                        filePathList.add(floderFile.getPath());
                    else
                        scanFolder(path + "\\" + filelist[i]);

                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
    }

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

    public String getModifiedTime(String filePath){
        File f = new File(filePath);
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }

    public List<String> readFileByLines(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        List<String> lineList = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
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
                }
            }
        }

        return lineList;
    }
}
