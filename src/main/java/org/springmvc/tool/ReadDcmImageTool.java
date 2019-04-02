package org.springmvc.tool;

import org.ini4j.Ini;
import org.ini4j.Wini;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReadDcmImageTool
 * @Author Bob
 * @Date 2019-04-01 16:28
 */
@Component
public class ReadDcmImageTool {
    /**
     *
     * @param filePath 文件路径
     * @return 返回几个序列的list集合
     */
    public List<List<String>> getImageSeriesList(String filePath){
        List<List<String>> seriesList = new ArrayList<List<String>>();
        File file = new File(filePath+"/seriesinfo");
        if (file.isFile()){
            System.out.println("给的不是一个图片目录，而是一个文件");
            return null;
        }else {
            try {
                Ini ini = new Ini(new File(filePath+"/seriesinfo/series.ini"));
                Ini.Section sneezy = ini.get("series");
               List<String> series = sneezy.getAll("series");
                System.out.println(series);
                for (int i=0;i<series.size();i++){
                    Ini iniSon = new Ini(new File(filePath+"/seriesinfo/"+series.get(i)+".ini"));
                    Ini.Section sneezySon = iniSon.get("filenames");
                    List<String> seriesImage = sneezySon.getAll("filename");
                    seriesList.add(seriesImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return seriesList;

    }

    public static void main(String[] aegs){
        ReadDcmImageTool readDcmImageTool = new ReadDcmImageTool();
        readDcmImageTool.getImageSeriesList("D:/dcmtest/DJ20170701B0018");
    }
}
