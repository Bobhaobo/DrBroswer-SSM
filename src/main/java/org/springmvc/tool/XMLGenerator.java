package org.springmvc.tool;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;


/**
 * @ClassName XMLGenerator
 * @Author Bob
 * @Date 2018-11-29 17:30
 */
@Component
public class XMLGenerator {
    /**
     *
     * @param folderName
     * @param fileName
     * @param filePath
     * @param picWidth
     * @param picHeight
     * @param picDepth
     * @param xOy
     */
    public void createXml(String folderName, String fileName, String filePath, String picWidth, String picHeight, String picDepth, float[][] xOy) {
        try {
            // 1、创建document对象
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点rss
            Element annotation = document.addElement("annotation");
            // 3、向rss节点添加version属性
            //rss.addAttribute("version", "2.0");
            // 4、生成子节点及子节点内容
            Element folder = annotation.addElement("folder");
            folder.setText(fileName);

            Element filename = annotation.addElement("filename");
            filename.setText(fileName);

            Element path = annotation.addElement("path");
            path.setText(filePath);

            Element source = annotation.addElement("source");
            Element database = source.addElement("database");
            database.setText("Unkonwn");

            Element size = annotation.addElement("size");
            Element width = size.addElement("width");
            width.setText(picWidth+"");
            Element height = size.addElement("height");
            height.setText(picHeight+"");
            Element depth = size.addElement("depth");
            depth.setText(picDepth);
            Element segmented = annotation.addElement("segmented");
            segmented.setText("0");
            for (int i=0;i<xOy.length;i++){
                Element object = annotation.addElement("object");
                Element name = object.addElement("name");
                name.setText("nodules");
                Element pose = object.addElement("pose");
                pose.setText("Unspecified");
                Element truncated = object.addElement("truncated");
                truncated.setText("0");
                Element difficult = object.addElement("difficult");
                difficult.setText("0");
                Element bndbox = object.addElement("bndbox");
                Element xmin = bndbox.addElement("xmin");
                xmin.setText(xOy[i][0]+"");
                Element ymin = bndbox.addElement("ymin");
                ymin.setText(xOy[i][1]+"");
                Element xmax = bndbox.addElement("xmax");
                xmax.setText(xOy[i][2]+"");
                Element ymax = bndbox.addElement("ymax");
                ymax.setText(xOy[i][3]+"");
            }

            //Element title = channel.addElement("title");
            //title.setText("国内最新新闻");
            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");

            String fileNam = getFileNameNoEx(fileName);
            // 6、生成xml文件
            File file = new File("D://xml//"+fileNam+".xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("生成rss.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成rss.xml失败");
        }
    }
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

}
